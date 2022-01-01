package com.roncoo.education.system.service.pc;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.service.pc.biz.PcApiNavBarBiz;
import com.roncoo.education.system.service.pc.req.*;
import com.roncoo.education.system.service.pc.resq.NavBarPageRESQ;
import com.roncoo.education.system.service.pc.resq.NavBarViewRESQ;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 头部导航
 */
@RestController
@RequestMapping(value = "/system/pc/nav/bar")
public class PcApiNavBarController extends BaseController {

    @Autowired
    private PcApiNavBarBiz biz;

    /**
     * 头部导航分页列表接口
     */
    @ApiOperation(value = "头部导航分页列表接口", notes = "头部导航分页列表接口")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result<Page<NavBarPageRESQ>> list(@RequestBody NavBarPageREQ navBarPageREQ) {
        return biz.list(navBarPageREQ);
    }

    /**
     * 头部导航保存接口
     */
    @ApiOperation(value = "头部导航保存接口", notes = "头部导航保存接口")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<Integer> save(@RequestBody NavBarSaveREQ navBarSaveREQ) {
        return biz.save(navBarSaveREQ);
    }

    /**
     * 头部导航删除接口
     */
    @ApiOperation(value = "头部导航删除接口", notes = "头部导航删除接口")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result<Integer> delete(@RequestBody NavBarDeleteREQ navBarDeleteREQ) {
        return biz.delete(navBarDeleteREQ);
    }

    /**
     * 头部导航更新接口
     */
    @ApiOperation(value = "头部导航更新接口", notes = "头部导航更新接口")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result<Integer> update(@RequestBody NavBarUpdateREQ navBarUpdateREQ) {
        return biz.update(navBarUpdateREQ);
    }

    /**
     * 头部导航查看接口
     */
    @ApiOperation(value = "头部导航查看接口", notes = "头部导航查看接口")
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public Result<NavBarViewRESQ> view(@RequestBody NavBarViewREQ navBarViewREQ) {
        return biz.view(navBarViewREQ);
    }

}
