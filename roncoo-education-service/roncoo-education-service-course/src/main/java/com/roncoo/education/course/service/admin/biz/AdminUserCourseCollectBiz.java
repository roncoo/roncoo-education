package com.roncoo.education.course.service.admin.biz;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.dao.UserCourseCollectDao;
import com.roncoo.education.course.dao.impl.mapper.entity.UserCourseCollect;
import com.roncoo.education.course.dao.impl.mapper.entity.UserCourseCollectExample;
import com.roncoo.education.course.dao.impl.mapper.entity.UserCourseCollectExample.Criteria;
import com.roncoo.education.course.service.admin.req.AdminUserCourseCollectEditReq;
import com.roncoo.education.course.service.admin.req.AdminUserCourseCollectPageReq;
import com.roncoo.education.course.service.admin.req.AdminUserCourseCollectSaveReq;
import com.roncoo.education.course.service.admin.resp.AdminUserCourseCollectPageResp;
import com.roncoo.education.course.service.admin.resp.AdminUserCourseCollectViewResp;
import com.roncoo.education.user.feign.interfaces.IFeignUsers;
import com.roncoo.education.user.feign.interfaces.vo.UsersVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ADMIN-课程收藏
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AdminUserCourseCollectBiz extends BaseBiz {
    @NotNull
    private final IFeignUsers feignUsers;

    @NotNull
    private final UserCourseCollectDao dao;

    /**
     * 课程收藏分页
     *
     * @param req 课程收藏分页查询参数
     * @return 课程收藏分页查询结果
     */
    public Result<Page<AdminUserCourseCollectPageResp>> page(AdminUserCourseCollectPageReq req) {
        UserCourseCollectExample example = new UserCourseCollectExample();
        Criteria c = example.createCriteria();
        if (ObjectUtil.isNotEmpty(req.getCourseId())) {
            c.andCourseIdEqualTo(req.getCourseId());
        }
        Page<UserCourseCollect> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminUserCourseCollectPageResp> respPage = PageUtil.transform(page, AdminUserCourseCollectPageResp.class);
        if (CollUtil.isNotEmpty(respPage.getList())) {
            List<Long> userIdList = respPage.getList().stream().map(item -> item.getUserId()).collect(Collectors.toList());
            Map<Long, UsersVO> usersVOMap = feignUsers.listByIds(userIdList);
            for (AdminUserCourseCollectPageResp resp : respPage.getList()) {
                UsersVO usersVO = usersVOMap.get(resp.getUserId());
                if (ObjectUtil.isNotEmpty(usersVO)) {
                    resp.setMobile(DesensitizedUtil.mobilePhone(usersVO.getMobile()));
                    resp.setNickname(usersVO.getNickname());
                }
            }
        }
        return Result.success(respPage);
    }

    /**
     * 课程收藏添加
     *
     * @param req 课程收藏
     * @return 添加结果
     */
    public Result<String> save(AdminUserCourseCollectSaveReq req) {
        UserCourseCollect record = BeanUtil.copyProperties(req, UserCourseCollect.class);
        if (dao.save(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 课程收藏查看
     *
     * @param id 主键ID
     * @return 课程收藏
     */
    public Result<AdminUserCourseCollectViewResp> view(Long id) {
        return Result.success(BeanUtil.copyProperties(dao.getById(id), AdminUserCourseCollectViewResp.class));
    }

    /**
     * 课程收藏修改
     *
     * @param req 课程收藏修改对象
     * @return 修改结果
     */
    public Result<String> edit(AdminUserCourseCollectEditReq req) {
        UserCourseCollect record = BeanUtil.copyProperties(req, UserCourseCollect.class);
        if (dao.updateById(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 课程收藏删除
     *
     * @param id ID主键
     * @return 删除结果
     */
    public Result<String> delete(Long id) {
        if (dao.deleteById(id) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }
}
