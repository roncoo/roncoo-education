package com.roncoo.education.system.service.api;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.service.api.biz.ApiWebsiteArticleBiz;
import com.roncoo.education.system.service.api.req.ApiWebsiteArticleReq;
import com.roncoo.education.system.service.api.resp.ApiWebsiteArticleResp;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 站点导航文章
 *
 * @author wuyun
 */
@RestController
@RequestMapping(value = "/system/api/website/article")
public class ApiWebsiteArticleController {

    @Autowired
    private ApiWebsiteArticleBiz biz;

    /**
     * 获取站点导航信息接口
     *
     * @return
     * @author wuyun
     */
    @ApiOperation(value = "获取站点导航信息接口", notes = "获取站点导航信息")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result<List<ApiWebsiteArticleResp>> list() {
        return biz.list();
    }

    /**
     * 获取站点导航文章接口
     *
     * @return
     * @author wuyun
     */
    @ApiOperation(value = "获取站点导航文章接口", notes = "根据站点导航ID获取站点导航文章信息")
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public Result<ApiWebsiteArticleResp> get(@RequestBody ApiWebsiteArticleReq req) {
        return biz.get(req);
    }
}
