package com.roncoo.education.system.service.api;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.service.api.biz.ApiWebsiteNavArticleBiz;
import com.roncoo.education.system.service.api.bo.WebsiteNavArticleBO;
import com.roncoo.education.system.service.api.dto.WebsiteNavArticleDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 站点导航文章
 *
 * @author wuyun
 */
@RestController
public class ApiWebsiteNavArticleController extends BaseController {

    @Autowired
    private ApiWebsiteNavArticleBiz biz;

    /**
     * 获取站点导航文章接口
     *
     * @return
     * @author wuyun
     */
    @ApiOperation(value = "获取站点导航文章接口", notes = "根据站点导航ID获取站点导航文章信息")
    @RequestMapping(value = "/system/api/website/nav/article/get", method = RequestMethod.POST)
    public Result<WebsiteNavArticleDTO> get(@RequestBody WebsiteNavArticleBO websiteNavArticleBO) {
        return biz.get(websiteNavArticleBO);
    }
}
