package com.roncoo.education.user.service.admin.biz;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.DesensitizedUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.user.dao.LogLoginDao;
import com.roncoo.education.user.dao.UsersDao;
import com.roncoo.education.user.dao.impl.mapper.entity.LogLogin;
import com.roncoo.education.user.dao.impl.mapper.entity.LogLoginExample;
import com.roncoo.education.user.dao.impl.mapper.entity.LogLoginExample.Criteria;
import com.roncoo.education.user.service.admin.req.AdminLogLoginEditReq;
import com.roncoo.education.user.service.admin.req.AdminLogLoginPageReq;
import com.roncoo.education.user.service.admin.req.AdminLogLoginSaveReq;
import com.roncoo.education.user.service.admin.resp.AdminLogLoginPageResp;
import com.roncoo.education.user.service.admin.resp.AdminLogLoginViewResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ADMIN-用户登录日志
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AdminLogLoginBiz extends BaseBiz {

    @NotNull
    private final LogLoginDao dao;
    @NotNull
    private final UsersDao usersDao;

    /**
     * 用户登录日志分页
     *
     * @param req 用户登录日志分页查询参数
     * @return 用户登录日志分页查询结果
     */
    public Result<Page<AdminLogLoginPageResp>> page(AdminLogLoginPageReq req) {
        LogLoginExample example = new LogLoginExample();
        Criteria c = example.createCriteria();
        example.setOrderByClause("id desc");
        Page<LogLogin> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminLogLoginPageResp> respPage = PageUtil.transform(page, AdminLogLoginPageResp.class);
        if (CollUtil.isNotEmpty(respPage.getList())) {
            List<Long> userIdList = respPage.getList().stream().map(AdminLogLoginPageResp::getUserId).collect(Collectors.toList());
            Map<Long, String> mobileMap = usersDao.listByIds(userIdList).stream().collect(Collectors.toMap(item -> item.getId(), item -> item.getMobile()));
            for (AdminLogLoginPageResp resp : respPage.getList()) {
                // 手机号
                resp.setMoblie(DesensitizedUtil.mobilePhone(mobileMap.get(resp.getUserId())));
            }
        }
        return Result.success(respPage);
    }

    /**
     * 用户登录日志添加
     *
     * @param req 用户登录日志
     * @return 添加结果
     */
    public Result<String> save(AdminLogLoginSaveReq req) {
        LogLogin record = BeanUtil.copyProperties(req, LogLogin.class);
        if (dao.save(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 用户登录日志查看
     *
     * @param id 主键ID
     * @return 用户登录日志
     */
    public Result<AdminLogLoginViewResp> view(Long id) {
        return Result.success(BeanUtil.copyProperties(dao.getById(id), AdminLogLoginViewResp.class));
    }

    /**
     * 用户登录日志修改
     *
     * @param req 用户登录日志修改对象
     * @return 修改结果
     */
    public Result<String> edit(AdminLogLoginEditReq req) {
        LogLogin record = BeanUtil.copyProperties(req, LogLogin.class);
        if (dao.updateById(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 用户登录日志删除
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
