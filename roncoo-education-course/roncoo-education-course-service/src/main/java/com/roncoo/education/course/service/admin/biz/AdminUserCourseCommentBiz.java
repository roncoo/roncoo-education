package com.roncoo.education.course.service.admin.biz;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.dao.UserCourseCommentDao;
import com.roncoo.education.course.dao.impl.mapper.entity.UserCourseComment;
import com.roncoo.education.course.dao.impl.mapper.entity.UserCourseCommentExample;
import com.roncoo.education.course.dao.impl.mapper.entity.UserCourseCommentExample.Criteria;
import com.roncoo.education.course.service.admin.req.AdminUserCourseCommentEditReq;
import com.roncoo.education.course.service.admin.req.AdminUserCourseCommentPageReq;
import com.roncoo.education.course.service.admin.req.AdminUserCourseCommentSaveReq;
import com.roncoo.education.course.service.admin.resp.AdminUserCourseCommentPageResp;
import com.roncoo.education.course.service.admin.resp.AdminUserCourseCommentViewResp;
import com.roncoo.education.user.feign.interfaces.IFeignUsers;
import com.roncoo.education.user.feign.interfaces.vo.UsersVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ADMIN-课程评论
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AdminUserCourseCommentBiz extends BaseBiz {
    @NotNull
    private final IFeignUsers feignUsers;
    @NotNull
    private final UserCourseCommentDao dao;

    /**
     * 课程评论分页
     *
     * @param req 课程评论分页查询参数
     * @return 课程评论分页查询结果
     */
    public Result<Page<AdminUserCourseCommentPageResp>> page(AdminUserCourseCommentPageReq req) {
        UserCourseCommentExample example = new UserCourseCommentExample();
        Criteria c = example.createCriteria();
        if (ObjectUtil.isNotEmpty(req.getCourseId())) {
            c.andCourseIdEqualTo(req.getCourseId());
        }
        Page<UserCourseComment> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminUserCourseCommentPageResp> respPage = PageUtil.transform(page, AdminUserCourseCommentPageResp.class);
        if (CollUtil.isNotEmpty(respPage.getList())) {
            List<Long> userIdList = respPage.getList().stream().map(item -> item.getUserId()).collect(Collectors.toList());
            Map<Long, UsersVO> usersVOMap = feignUsers.listByIds(userIdList);
            for (AdminUserCourseCommentPageResp resp : respPage.getList()) {
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
     * 课程评论添加
     *
     * @param req 课程评论
     * @return 添加结果
     */
    public Result<String> save(AdminUserCourseCommentSaveReq req) {
        UserCourseComment record = BeanUtil.copyProperties(req, UserCourseComment.class);
        if (dao.save(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 课程评论查看
     *
     * @param id 主键ID
     * @return 课程评论
     */
    public Result<AdminUserCourseCommentViewResp> view(Long id) {
        return Result.success(BeanUtil.copyProperties(dao.getById(id), AdminUserCourseCommentViewResp.class));
    }

    /**
     * 课程评论修改
     *
     * @param req 课程评论修改对象
     * @return 修改结果
     */
    public Result<String> edit(AdminUserCourseCommentEditReq req) {
        UserCourseComment record = BeanUtil.copyProperties(req, UserCourseComment.class);
        if (dao.updateById(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 课程评论删除
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
