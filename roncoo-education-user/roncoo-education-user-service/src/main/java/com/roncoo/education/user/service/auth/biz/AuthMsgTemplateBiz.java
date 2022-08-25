package com.roncoo.education.user.service.auth.biz;

import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.user.dao.MsgTemplateDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * AUTH-消息模板
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AuthMsgTemplateBiz extends BaseBiz {

    @NotNull
    private final MsgTemplateDao dao;

}
