package com.roncoo.education.user.service.auth.biz;

import com.roncoo.education.common.config.ThreadContext;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.common.service.BaseWxBiz;
import com.roncoo.education.system.feign.interfaces.IFeignSysConfig;
import com.roncoo.education.system.feign.interfaces.vo.LoginConfig;
import com.roncoo.education.user.dao.UsersAccountDao;
import com.roncoo.education.user.dao.UsersDao;
import com.roncoo.education.user.dao.impl.mapper.entity.Users;
import com.roncoo.education.user.dao.impl.mapper.entity.UsersAccount;
import com.roncoo.education.user.service.auth.req.AuthBindingReq;
import com.roncoo.education.user.service.auth.req.AuthUsersReq;
import com.roncoo.education.user.service.auth.resp.AuthUsersResp;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * AUTH-用户信息
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AuthUsersBiz extends BaseBiz {

    @NotNull
    private final UsersDao dao;
    @NotNull
    private final UsersAccountDao usersAccountDao;
    @NotNull
    private final IFeignSysConfig feignSysConfig;
    @NotNull
    private final BaseWxBiz baseWxBiz;

    public Result<AuthUsersResp> view() {
        Users users = dao.getById(ThreadContext.userId());
        if (users != null && users.getStatusId().equals(StatusIdEnum.YES.getCode())) {
            AuthUsersResp resp = BeanUtil.copyProperties(users, AuthUsersResp.class);
            UsersAccount usersAccount = usersAccountDao.getByUserId(users.getId());
            if (usersAccount != null) {
                resp.setAvailableAmount(usersAccount.getAvailableAmount());
                resp.setFreezeAmount(usersAccount.getFreezeAmount());
            }
            return Result.success(resp);
        }
        return Result.error("用户不存在或被禁用");
    }

    public Result<String> update(AuthUsersReq req) {
        Users users = BeanUtil.copyProperties(req, Users.class);
        users.setId(ThreadContext.userId());
        int result = dao.updateById(users);
        if (result > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    public Result<String> binding(AuthBindingReq req) throws WxErrorException {
        Users users = dao.getById(ThreadContext.userId());

        // 获取微信用户信息
        LoginConfig loginConfig = feignSysConfig.getLogin();
        WxOAuth2UserInfo userInfo = baseWxBiz.getAuthInfo(loginConfig.getWxPcLoginAppId(), loginConfig.getWxPcLoginAppSecret(), req.getCode());

        // 更新用户信息
        Users newUser = new Users();
        newUser.setId(users.getId());
        newUser.setUnionId(userInfo.getUnionId());
        newUser.setOpenId(userInfo.getOpenid());
        dao.updateById(newUser);
        return Result.success("操作成功");
    }

    /**
     * 解绑微信
     *
     * @return
     */
    public Result<String> unbind() {
        Users users = dao.getById(ThreadContext.userId());
        Users newUsers = new Users();
        newUsers.setId(users.getId());
        newUsers.setUnionId("");
        newUsers.setOpenId("");
        dao.updateById(newUsers);
        return Result.success("操作成功");
    }
}
