package com.roncoo.education.user.service.admin.biz;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.tools.BeanUtil;
import com.roncoo.education.common.base.BaseBiz;
import com.roncoo.education.user.dao.UsersDao;
import com.roncoo.education.user.dao.UsersLogDao;
import com.roncoo.education.user.dao.impl.mapper.entity.UsersLog;
import com.roncoo.education.user.dao.impl.mapper.entity.UsersLogExample;
import com.roncoo.education.user.service.admin.req.AdminUsersLogEditReq;
import com.roncoo.education.user.service.admin.req.AdminUsersLogPageReq;
import com.roncoo.education.user.service.admin.req.AdminUsersLogSaveReq;
import com.roncoo.education.user.service.admin.resp.AdminUsersLogPageResp;
import com.roncoo.education.user.service.admin.resp.AdminUsersLogViewResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotNull;

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
public class AdminUsersLogBiz extends BaseBiz {

    @NotNull
    private final UsersLogDao dao;
    @NotNull
    private final UsersDao usersDao;

    /**
     * 用户登录日志分页
     *
     * @param req 用户登录日志分页查询参数
     * @return 用户登录日志分页查询结果
     */
    public Result<Page<AdminUsersLogPageResp>> page(AdminUsersLogPageReq req) {
        UsersLogExample example = new UsersLogExample();
        UsersLogExample.Criteria c = example.createCriteria();
        if (ObjectUtil.isNotEmpty(req.getLoginClient())) {
            c.andLoginClientEqualTo(req.getLoginClient());
        }
        if (ObjectUtil.isNotEmpty(req.getLoginType())) {
            c.andLoginTypeEqualTo(req.getLoginType());
        }
        example.setOrderByClause("id desc");
        Page<UsersLog> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminUsersLogPageResp> respPage = PageUtil.transform(page, AdminUsersLogPageResp.class);
        if (CollUtil.isNotEmpty(respPage.getList())) {
            List<Long> userIdList = respPage.getList().stream().map(AdminUsersLogPageResp::getUserId).collect(Collectors.toList());
            Map<Long, String> mobileMap = usersDao.listByIds(userIdList).stream().collect(Collectors.toMap(item -> item.getId(), item -> item.getMobile()));
            for (AdminUsersLogPageResp resp : respPage.getList()) {
                // 手机号
                resp.setMobile(DesensitizedUtil.mobilePhone(mobileMap.get(resp.getUserId())));
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
    public Result<String> save(AdminUsersLogSaveReq req) {
        UsersLog record = BeanUtil.copyProperties(req, UsersLog.class);
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
    public Result<AdminUsersLogViewResp> view(Long id) {
        return Result.success(BeanUtil.copyProperties(dao.getById(id), AdminUsersLogViewResp.class));
    }

    /**
     * 用户登录日志修改
     *
     * @param req 用户登录日志修改对象
     * @return 修改结果
     */
    public Result<String> edit(AdminUsersLogEditReq req) {
        UsersLog record = BeanUtil.copyProperties(req, UsersLog.class);
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
