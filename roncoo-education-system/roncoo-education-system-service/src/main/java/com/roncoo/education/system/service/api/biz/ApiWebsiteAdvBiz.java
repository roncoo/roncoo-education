package com.roncoo.education.system.service.api.biz;

import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.system.dao.WebsiteAdvDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * API-广告信息
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class ApiWebsiteAdvBiz extends BaseBiz {

    @NotNull
    private final WebsiteAdvDao dao;

}
