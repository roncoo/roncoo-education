package com.roncoo.education.user.service.auth.biz;

import com.roncoo.education.common.config.ThreadContext;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.user.dao.UsersDao;
import com.roncoo.education.user.dao.impl.mapper.entity.Users;
import com.roncoo.education.user.service.auth.req.AuthUsersReq;
import com.roncoo.education.user.service.auth.resp.AuthUsersResp;
import lombok.RequiredArgsConstructor;
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

    public Result<AuthUsersResp> view() {
        Users users = dao.getById(ThreadContext.userId());
        if (users != null && users.getStatusId().equals(StatusIdEnum.YES.getCode())) {
            return Result.success(BeanUtil.copyProperties(users, AuthUsersResp.class));
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
}
