package com.roncoo.education.user.service.api;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.user.service.api.biz.ApiRegionBiz;
import com.roncoo.education.user.service.api.req.RegionCityIdReq;
import com.roncoo.education.user.service.api.req.RegionLevelReq;
import com.roncoo.education.user.service.api.req.RegionProvinceReq;
import com.roncoo.education.user.service.api.resp.RegionResp;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * 行政区域表
 *
 * @author wujing
 */
@Tag(name = "api-行政区域")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user/api/region")
public class ApiRegionController {

    @NotNull
    private final ApiRegionBiz biz;

    /**
     * 区域列出接口
     */
    @Operation(summary = "区域列出接口", description = "根据级别获取区域列出信息")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result<List<RegionResp>> listForLevel(@RequestBody RegionLevelReq userRegionLevelBO) {
        return biz.listForLevel(userRegionLevelBO);
    }

    /**
     * 区域列出接口
     */
    @Operation(summary = "区域列出接口", description = "根据provinceId获取区域列表信息")
    @RequestMapping(value = "/list/province", method = RequestMethod.POST)
    public Result<List<RegionResp>> listForProvince(@RequestBody RegionProvinceReq userRegionProvinceBO) {
        return biz.listForProvince(userRegionProvinceBO);
    }

    /**
     * 区域列出接口
     */
    @Operation(summary = "区域列出接口", description = "根据cityId获取区域列表信息")
    @RequestMapping(value = "/list/city", method = RequestMethod.POST)
    public Result<List<RegionResp>> listForCity(@RequestBody RegionCityIdReq userRegionCityIdBO) {
        return biz.listForCity(userRegionCityIdBO);
    }

}
