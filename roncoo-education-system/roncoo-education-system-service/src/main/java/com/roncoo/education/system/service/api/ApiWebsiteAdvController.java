package com.roncoo.education.system.service.api;

import com.roncoo.education.system.service.api.biz.ApiWebsiteAdvBiz;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * API-广告信息
 *
 * @author wujing
 * @date 2022-08-25
 */
@Api(tags = "API-广告信息")
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/api/website/adv")
public class ApiWebsiteAdvController {

    @NotNull
    private final ApiWebsiteAdvBiz biz;

}
