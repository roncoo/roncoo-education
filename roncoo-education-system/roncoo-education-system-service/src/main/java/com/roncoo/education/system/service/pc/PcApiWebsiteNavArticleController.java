package com.roncoo.education.system.service.pc;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.service.pc.biz.PcApiWebsiteNavArticleBiz;
import com.roncoo.education.system.service.pc.req.WebsiteNavArticleSaveREQ;
import com.roncoo.education.system.service.pc.req.WebsiteNavArticleUpdateREQ;
import com.roncoo.education.system.service.pc.req.WebsiteNavArticleViewREQ;
import com.roncoo.education.system.service.pc.resq.WebsiteNavArticleViewRESQ;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 站点导航文章
 */
@RestController
@RequestMapping(value = "/system/pc/website/nav/article")
public class PcApiWebsiteNavArticleController extends BaseController {

    @Autowired
    private PcApiWebsiteNavArticleBiz biz;

    /**
     * 获取站点导航文章接口
     *
     * @return
     */
    @ApiOperation(value = "获取站点导航文章接口", notes = "获取站点导航文章信息")
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public Result<WebsiteNavArticleViewRESQ> view(@RequestBody WebsiteNavArticleViewREQ websiteNavArticleViewREQ) {
        return biz.view(websiteNavArticleViewREQ);
    }

    /**
     * 获取站点导航文章接口
     *
     * @return
     */
    @ApiOperation(value = "添加站点导航文章接口", notes = "获取站点导航文章信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<Integer> save(@RequestBody WebsiteNavArticleSaveREQ websiteNavArticleSaveREQ) {
        return biz.save(websiteNavArticleSaveREQ);
    }

    /**
     * 更新站点导航文章接口
     *
     * @return
     */
    @ApiOperation(value = "更新站点导航文章接口", notes = "更新站点导航文章信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result<Integer> update(@RequestBody WebsiteNavArticleUpdateREQ websiteNavArticleUpdateREQ) {
        return biz.update(websiteNavArticleUpdateREQ);
    }
}
