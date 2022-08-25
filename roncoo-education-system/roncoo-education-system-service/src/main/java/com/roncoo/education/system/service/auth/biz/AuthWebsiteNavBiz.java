package com.roncoo.education.system.service.auth.biz;

import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.system.dao.WebsiteNavDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * AUTH-头部导航
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AuthWebsiteNavBiz extends BaseBiz {

    @NotNull
    private final WebsiteNavDao dao;

}
