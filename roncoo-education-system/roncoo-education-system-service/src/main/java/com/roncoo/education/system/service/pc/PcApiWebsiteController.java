package com.roncoo.education.system.service.pc;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.service.pc.biz.PcApiWebsiteBiz;
import com.roncoo.education.system.service.pc.req.WebsiteUpdateREQ;
import com.roncoo.education.system.service.pc.resq.WebsiteViewRESQ;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 站点信息
 *
 * @author wuyun
 */
@RestController
@RequestMapping(value = "/system/pc/website")
public class PcApiWebsiteController extends BaseController {

    @Autowired
    private PcApiWebsiteBiz biz;

    /**
     * 获取站点信息接口
     *
     * @return 站点信息
     */
    @ApiOperation(value = "获取站点信息接口", notes = "获取站点信息")
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public Result<WebsiteViewRESQ> view() {
        return biz.view();
    }

    /**
     * 更新站点信息接口
     */
    @ApiOperation(value = "更新站点信息接口", notes = "更新站点信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result<Integer> update(@RequestBody WebsiteUpdateREQ websiteUpdateREQ) {
        return biz.update(websiteUpdateREQ);
    }

}
