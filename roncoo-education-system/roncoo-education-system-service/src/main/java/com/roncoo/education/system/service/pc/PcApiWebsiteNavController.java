package com.roncoo.education.system.service.pc;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.service.pc.biz.PcApiWebsiteNavBiz;
import com.roncoo.education.system.service.pc.req.*;
import com.roncoo.education.system.service.pc.resq.WebsiteNavPageRESQ;
import com.roncoo.education.system.service.pc.resq.WebsiteNavViewRESQ;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 站点导航
 */
@RestController
@RequestMapping(value = "/system/pc/website/nav")
public class PcApiWebsiteNavController extends BaseController {

    @Autowired
    private PcApiWebsiteNavBiz biz;

    /**
     * 底部导航分页列表接口
     */
    @ApiOperation(value = "底部导航分页列表接口", notes = "底部导航分页列表接口")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result<Page<WebsiteNavPageRESQ>> list(@RequestBody WebsiteNavPageREQ websiteNavPageREQ) {
        return biz.list(websiteNavPageREQ);
    }

    /**
     * 底部导航保存接口
     */
    @ApiOperation(value = "底部导航保存接口", notes = "底部导航保存接口")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<Integer> save(@RequestBody WebsiteNavSaveREQ websiteNavSaveREQ) {
        return biz.save(websiteNavSaveREQ);
    }

    /**
     * 底部导航删除接口
     */
    @ApiOperation(value = "底部导航删除接口", notes = "底部导航删除接口")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result<Integer> delete(@RequestBody WebsiteNavDeleteREQ websiteNavDeleteREQ) {
        return biz.delete(websiteNavDeleteREQ);
    }

    /**
     * 底部导航更新接口
     */
    @ApiOperation(value = "底部导航更新接口", notes = "底部导航更新接口")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result<Integer> update(@RequestBody WebsiteNavUpdateREQ websiteNavUpdateREQ) {
        return biz.update(websiteNavUpdateREQ);
    }

    /**
     * 底部导航查看接口
     */
    @ApiOperation(value = "底部导航查看接口", notes = "底部导航查看接口")
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public Result<WebsiteNavViewRESQ> view(@RequestBody WebsiteNavViewREQ websiteNavViewREQ) {
        return biz.view(websiteNavViewREQ);
    }

}
