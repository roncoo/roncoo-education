package com.roncoo.education.user.service.auth.biz;

import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.user.dao.LogSmsDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * AUTH-用户短信发送日志
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AuthLogSmsBiz extends BaseBiz {

    @NotNull
    private final LogSmsDao dao;

}
