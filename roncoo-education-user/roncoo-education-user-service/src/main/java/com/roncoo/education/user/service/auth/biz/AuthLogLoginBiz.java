package com.roncoo.education.user.service.auth.biz;

import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.user.dao.LogLoginDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * AUTH-用户登录日志
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AuthLogLoginBiz extends BaseBiz {

    @NotNull
    private final LogLoginDao dao;

}
