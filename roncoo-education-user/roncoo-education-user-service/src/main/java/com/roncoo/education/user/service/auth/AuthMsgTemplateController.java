package com.roncoo.education.user.service.auth;

import com.roncoo.education.user.service.auth.biz.AuthMsgTemplateBiz;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * AUTH-消息模板
 *
 * @author wujing
 * @date 2022-08-25
 */
@Api(tags = "AUTH-消息模板")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/auth/msg/template")
public class AuthMsgTemplateController {

    @NotNull
    private final AuthMsgTemplateBiz biz;

}
