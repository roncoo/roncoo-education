package com.roncoo.education.system.service.api.biz;

import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.system.dao.WebsiteAppDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * API-app版本管理
 *
 * @author fengyw
 */
@Component
@RequiredArgsConstructor
public class ApiWebsiteAppBiz extends BaseBiz {

    @NotNull
    private final WebsiteAppDao dao;

}
