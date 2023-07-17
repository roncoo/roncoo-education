package com.roncoo.education.system.service.api;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.service.api.biz.ApiWebsiteLinkBiz;
import com.roncoo.education.system.service.api.resp.ApiWebsiteLinkResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 友情链接
 *
 * @author wuyun
 */
@Api(tags = "api-友情链接")
@RestController
@RequestMapping(value = "/system/api/website/link")
public class ApiWebsiteLinkController {

    @Autowired
    private ApiWebsiteLinkBiz biz;

    /**
     * 友情链接接口
     *
     * @return 返回友情链接列表
     * @author wuyun
     */
    @ApiOperation(value = "友情链接接口", notes = "返回友情链接列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result<List<ApiWebsiteLinkResp>> list() {
        return biz.list();
    }

}
