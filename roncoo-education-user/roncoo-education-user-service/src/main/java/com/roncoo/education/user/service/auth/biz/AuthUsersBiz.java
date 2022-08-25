package com.roncoo.education.user.service.auth.biz;

import com.roncoo.education.common.service.BaseBiz;
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

}
