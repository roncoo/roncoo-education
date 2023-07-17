package com.roncoo.education.system.service.api;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.service.api.biz.ApiWebsiteCarouselBiz;
import com.roncoo.education.system.service.api.resp.ApiWebsiteCarouselResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * API-广告信息
 *
 * @author wujing
 * @date 2022-09-16
 */
@Api(tags = "api-广告信息")
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/api/website/carousel")
public class ApiWebsiteCarouselController {

    @NotNull
    private final ApiWebsiteCarouselBiz biz;

    @ApiOperation(value = "广告列表接口", notes = "首页轮播广告列出")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result<List<ApiWebsiteCarouselResp>> list() {
        return biz.list();
    }

}
