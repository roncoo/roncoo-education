package com.roncoo.education.user.service.admin.biz;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.tools.BeanUtil;
import com.roncoo.education.common.tools.Md5Util;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.user.dao.UsersAccountDao;
import com.roncoo.education.user.dao.UsersDao;
import com.roncoo.education.user.dao.impl.mapper.entity.Users;
import com.roncoo.education.user.dao.impl.mapper.entity.UsersAccount;
import com.roncoo.education.user.dao.impl.mapper.entity.UsersExample;
import com.roncoo.education.user.dao.impl.mapper.entity.UsersExample.Criteria;
import com.roncoo.education.user.service.admin.req.AdminUsersEditReq;
import com.roncoo.education.user.service.admin.req.AdminUsersPageReq;
import com.roncoo.education.user.service.admin.req.AdminUsersSaveReq;
import com.roncoo.education.user.service.admin.resp.AdminUsersAccountViewResp;
import com.roncoo.education.user.service.admin.resp.AdminUsersPageResp;
import com.roncoo.education.user.service.admin.resp.AdminUsersViewResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @NotNull
    private final UsersAccountDao usersAccountDao;

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
            List<UsersAccount> usersAccounts = usersAccountDao.listByUserIds(respPage.getList().stream().map(AdminUsersPageResp::getId).collect(Collectors.toList()));
            Map<Long, AdminUsersAccountViewResp> usersAccountMap = null;
            if (CollUtil.isNotEmpty(usersAccounts)) {
                usersAccountMap = usersAccounts.stream().collect(Collectors.toMap(UsersAccount::getUserId, item -> BeanUtil.copyProperties(item, AdminUsersAccountViewResp.class)));
            }
            for (AdminUsersPageResp resp : respPage.getList()) {
                // 脱敏处理
                resp.setMobile(DesensitizedUtil.mobilePhone(resp.getMobile()));
                // 账户信息
                if (usersAccountMap != null) {
                    resp.setUsersAccountViewResp(usersAccountMap.get(resp.getId()));
                }
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
        AdminUsersViewResp usersViewResp = BeanUtil.copyProperties(dao.getById(id), AdminUsersViewResp.class);
        UsersAccount account = usersAccountDao.getByUserId(id);
        if (ObjectUtil.isEmpty(account)) {
            /// 用户账户不存在，创建新账户
            account = new UsersAccount();
            account.setUserId(id);
            account.setAvailableAmount(BigDecimal.ZERO);
            account.setFreezeAmount(BigDecimal.ZERO);
            account.setSign(Md5Util.md5(account.getUserId().toString(), account.getAvailableAmount().toPlainString(), account.getFreezeAmount().toPlainString()));
            usersAccountDao.save(account);
        }
        usersViewResp.setUsersAccountViewResp(BeanUtil.copyProperties(account, AdminUsersAccountViewResp.class));
        return Result.success(usersViewResp);
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
