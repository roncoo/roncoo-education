package com.roncoo.education.system.service.api;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.service.api.biz.ApiWebsiteLinkBiz;
import com.roncoo.education.system.service.api.dto.WebsiteLinkListDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 站点友情链接
 *
 * @author wuyun
 */
@RestController
public class ApiWebsiteLinkController extends BaseController {

    @Autowired
    private ApiWebsiteLinkBiz biz;

    /**
     * 获取站点友情链接接口
     *
     * @return 站点友情链接
     * @author wuyun
     */
    @ApiOperation(value = "获取站点友情链接接口", notes = "获取站点友情链接")
    @RequestMapping(value = "/system/api/website/link", method = RequestMethod.POST)
    public Result<WebsiteLinkListDTO> list() {
        return biz.list();
    }

}
