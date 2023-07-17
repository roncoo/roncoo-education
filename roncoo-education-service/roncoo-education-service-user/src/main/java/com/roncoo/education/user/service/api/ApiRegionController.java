package com.roncoo.education.user.service.api;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.user.service.api.biz.ApiRegionBiz;
import com.roncoo.education.user.service.api.req.RegionCityIdReq;
import com.roncoo.education.user.service.api.req.RegionLevelReq;
import com.roncoo.education.user.service.api.req.RegionProvinceReq;
import com.roncoo.education.user.service.api.resp.RegionResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 行政区域表
 *
 * @author wujing
 */
@Api(tags = "api-行政区域")
@RestController
@RequestMapping(value = "/user/api/region")
public class ApiRegionController {

    @Autowired
    private ApiRegionBiz biz;

    /**
     * 区域列出接口
     */
    @ApiOperation(value = "区域列出接口", notes = "根据级别获取区域列出信息")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result<List<RegionResp>> listForLevel(@RequestBody RegionLevelReq userRegionLevelBO) {
        return biz.listForLevel(userRegionLevelBO);
    }

    /**
     * 区域列出接口
     */
    @ApiOperation(value = "区域列出接口", notes = "根据provinceId获取区域列表信息")
    @RequestMapping(value = "/list/province", method = RequestMethod.POST)
    public Result<List<RegionResp>> listForProvince(@RequestBody RegionProvinceReq userRegionProvinceBO) {
        return biz.listForProvince(userRegionProvinceBO);
    }

    /**
     * 区域列出接口
     */
    @ApiOperation(value = "区域列出接口", notes = "根据cityId获取区域列表信息")
    @RequestMapping(value = "/list/city", method = RequestMethod.POST)
    public Result<List<RegionResp>> listForCity(@RequestBody RegionCityIdReq userRegionCityIdBO) {
        return biz.listForCity(userRegionCityIdBO);
    }

}
