package com.roncoo.education.system.service.pc;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.service.pc.biz.PcApiSysBiz;
import com.roncoo.education.system.service.pc.req.SysUpdateRESQ;
import com.roncoo.education.system.service.pc.resq.SysViewRESQ;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统配置表
 */
@RestController
@RequestMapping(value = "/system/pc/sys")
public class PcApiSysController extends BaseController {

    @Autowired
    private PcApiSysBiz biz;

    /**
     * 获取系统配置信息接口
     */
    @ApiOperation(value = "获取系统配置信息接口", notes = "获取系统配置信息")
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public Result<SysViewRESQ> view() {
        return biz.view();
    }

    /**
     * 更新系统配置信息接口
     */
    @ApiOperation(value = "更新系统配置信息接口", notes = "更新系统配置信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result<Integer> update(@RequestBody SysUpdateRESQ sysUpdateRESQ) {
        return biz.update(sysUpdateRESQ);
    }

}
