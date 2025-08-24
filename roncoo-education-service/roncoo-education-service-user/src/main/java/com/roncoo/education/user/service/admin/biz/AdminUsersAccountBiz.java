package com.roncoo.education.user.service.admin.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.user.dao.UsersAccountDao;
import com.roncoo.education.user.dao.impl.mapper.entity.UsersAccount;
import com.roncoo.education.user.dao.impl.mapper.entity.UsersAccountExample;
import com.roncoo.education.user.dao.impl.mapper.entity.UsersAccountExample.Criteria;
import com.roncoo.education.user.service.admin.req.AdminUsersAccountEditReq;
import com.roncoo.education.user.service.admin.req.AdminUsersAccountPageReq;
import com.roncoo.education.user.service.admin.req.AdminUsersAccountSaveReq;
import com.roncoo.education.user.service.admin.resp.AdminUsersAccountPageResp;
import com.roncoo.education.user.service.admin.resp.AdminUsersAccountViewResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotNull;

/**
 * ADMIN-用户账户
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AdminUsersAccountBiz extends BaseBiz {

    @NotNull
    private final UsersAccountDao dao;

    /**
     * 用户账户分页
     *
     * @param req 用户账户分页查询参数
     * @return 用户账户分页查询结果
     */
    public Result<Page<AdminUsersAccountPageResp>> page(AdminUsersAccountPageReq req) {
        UsersAccountExample example = new UsersAccountExample();
        Criteria c = example.createCriteria();
        Page<UsersAccount> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminUsersAccountPageResp> respPage = PageUtil.transform(page, AdminUsersAccountPageResp.class);
        return Result.success(respPage);
    }

    /**
     * 用户账户添加
     *
     * @param req 用户账户
     * @return 添加结果
     */
    public Result<String> save(AdminUsersAccountSaveReq req) {
        UsersAccount record = BeanUtil.copyProperties(req, UsersAccount.class);
        if (dao.save(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 用户账户查看
     *
     * @param id 主键ID
     * @return 用户账户
     */
    public Result<AdminUsersAccountViewResp> view(Long id) {
        return Result.success(BeanUtil.copyProperties(dao.getById(id), AdminUsersAccountViewResp.class));
    }

    /**
     * 用户账户修改
     *
     * @param req 用户账户修改对象
     * @return 修改结果
     */
    public Result<String> edit(AdminUsersAccountEditReq req) {
        UsersAccount record = BeanUtil.copyProperties(req, UsersAccount.class);
        if (dao.updateById(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 用户账户删除
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
