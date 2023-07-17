package com.roncoo.education.system.service.admin;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.service.admin.biz.AdminSysLogBiz;
import com.roncoo.education.system.service.admin.req.AdminSysLogPageReq;
import com.roncoo.education.system.service.admin.resp.AdminSysLogPageResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 后台操作日志表
 *
 * @author wujing
 */
@Api(value = "admin-日志")
@RestController
@RequestMapping(value = "/system/admin/sys/log")
public class AdminSysLogController {

    @Autowired
    private AdminSysLogBiz biz;

    /**
     * 后台操作日志分页列表接口
     */
    @ApiOperation(value = "后台操作日志分页列表接口", notes = "后台操作日志分页列表接口")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result<Page<AdminSysLogPageResp>> list(@RequestBody AdminSysLogPageReq req) {
        return biz.list(req);
    }

}
