package com.roncoo.education.system.service.admin;

import com.roncoo.education.common.annotation.SysLog;
import com.roncoo.education.common.annotation.SysLogCache;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.service.admin.biz.AdminSysMenuBiz;
import com.roncoo.education.system.service.admin.req.*;
import com.roncoo.education.system.service.admin.resp.AdminSysMenuResp;
import com.roncoo.education.system.service.admin.resp.AdminSysMenuViewResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 菜单信息
 *
 * @author wujing
 */
@Api(tags = "admin-菜单接口")
@RestController
@RequestMapping(value = "/system/admin/sys/menu")
public class AdminSysMenuController {

    @Autowired
    private AdminSysMenuBiz biz;

    /**
     * 菜单信息列出信息接口
     */
    @ApiOperation(value = "菜单列出", notes = "根据条件列出菜单")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result<List<AdminSysMenuResp>> list(@RequestBody AdminSysMenuListReq sysMenuListReq) {
        return biz.list(sysMenuListReq);
    }

    /**
     * 菜单信息添加信息接口
     */
    @ApiOperation(value = "菜单添加", notes = "菜单添加")
    @SysLog(value = "菜单添加")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<String> save(@RequestBody AdminSysMenuSaveReq sysMenuSaveReq) {
        return biz.save(sysMenuSaveReq);
    }

    /**
     * 菜单信息删除接口
     */
    @ApiOperation(value = "菜单删除", notes = "根据ID删除菜单")
    @SysLog(value = "菜单删除")
    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    public Result<String> delete(@RequestBody AdminSysMenuDeleteReq sysMenuDeleteReq) {
        return biz.delete(sysMenuDeleteReq);
    }

    /**
     * 菜单信息修改接口
     */
    @ApiOperation(value = "菜单修改", notes = "菜单修改")
    @SysLog(value = "菜单修改")
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public Result<String> update(@RequestBody AdminSysMenuUpdateReq sysMenuUpdateReq) {
        return biz.update(sysMenuUpdateReq);
    }

    /**
     * 菜单信息查看接口
     */
    @ApiOperation(value = "菜单信息查看接口", notes = "菜单信息查看接口")
    @SysLogCache
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public Result<AdminSysMenuViewResp> view(@RequestBody AdminSysMenuViewReq sysMenuViewREQ) {
        return biz.view(sysMenuViewREQ);
    }

}
