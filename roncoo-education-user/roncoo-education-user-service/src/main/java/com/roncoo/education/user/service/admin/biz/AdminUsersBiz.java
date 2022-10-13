package com.roncoo.education.user.service.admin.biz;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.DesensitizedUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.user.dao.UsersDao;
import com.roncoo.education.user.dao.impl.mapper.entity.Users;
import com.roncoo.education.user.dao.impl.mapper.entity.UsersExample;
import com.roncoo.education.user.dao.impl.mapper.entity.UsersExample.Criteria;
import com.roncoo.education.user.service.admin.req.AdminUsersEditReq;
import com.roncoo.education.user.service.admin.req.AdminUsersPageReq;
import com.roncoo.education.user.service.admin.req.AdminUsersSaveReq;
import com.roncoo.education.user.service.admin.resp.AdminUsersPageResp;
import com.roncoo.education.user.service.admin.resp.AdminUsersViewResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;

/**
 * ADMIN-用户信息
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AdminUsersBiz extends BaseBiz {

    @NotNull
    private final UsersDao dao;

    /**
     * 用户信息分页
     *
     * @param req 用户信息分页查询参数
     * @return 用户信息分页查询结果
     */
    public Result<Page<AdminUsersPageResp>> page(AdminUsersPageReq req) {
        UsersExample example = new UsersExample();
        Criteria c = example.createCriteria();
        if (StringUtils.hasText(req.getMobile())) {
            c.andMobileLike(PageUtil.rightLike(req.getMobile()));
        }
        example.setOrderByClause("id desc");
        Page<Users> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminUsersPageResp> respPage = PageUtil.transform(page, AdminUsersPageResp.class);
        if (CollUtil.isNotEmpty(respPage.getList())) {
            // 脱敏处理
            for (AdminUsersPageResp resp : respPage.getList()) {
                resp.setMobile(DesensitizedUtil.mobilePhone(resp.getMobile()));
            }
        }
        return Result.success(respPage);
    }

    /**
     * 用户信息添加
     *
     * @param req 用户信息
     * @return 添加结果
     */
    public Result<String> save(AdminUsersSaveReq req) {
        Users record = BeanUtil.copyProperties(req, Users.class);
        if (dao.save(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 用户信息查看
     *
     * @param id 主键ID
     * @return 用户信息
     */
    public Result<AdminUsersViewResp> view(Long id) {
        return Result.success(BeanUtil.copyProperties(dao.getById(id), AdminUsersViewResp.class));
    }

    /**
     * 用户信息修改
     *
     * @param req 用户信息修改对象
     * @return 修改结果
     */
    public Result<String> edit(AdminUsersEditReq req) {
        Users record = BeanUtil.copyProperties(req, Users.class);
        if (dao.updateById(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 用户信息删除
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
