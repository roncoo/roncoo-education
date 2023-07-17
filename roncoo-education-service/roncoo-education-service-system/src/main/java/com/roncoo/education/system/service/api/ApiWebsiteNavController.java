package com.roncoo.education.system.service.api;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.service.api.biz.ApiWebsiteNavBiz;
import com.roncoo.education.system.service.api.resp.ApiWebsiteNavResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 头部导航
 *
 * @author wuyun
 */
@Api(tags = "api-站点信息")
@RestController
@RequestMapping(value = "/system/api/website/nav")
public class ApiWebsiteNavController {

    @Autowired
    private ApiWebsiteNavBiz biz;

    /**
     * 头部导航接口
     *
     * @return 头部导航列表
     * @author wuyun
     */
    @ApiOperation(value = "头部导航接口", notes = "返回头部导航列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result<List<ApiWebsiteNavResp>> list() {
        return biz.list();
    }

}
