package com.roncoo.education.system.service.admin;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.service.admin.biz.AdminSysMenuBiz;
import com.roncoo.education.system.service.admin.req.*;
import com.roncoo.education.system.service.admin.resp.AdminSysMenuResp;
import com.roncoo.education.system.service.admin.resp.AdminSysMenuUserResp;
import com.roncoo.education.system.service.admin.resp.AdminSysMenuViewResp;
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
@RestController
@RequestMapping(value = "/system/admin/sys/menu")
public class AdminSysMenuController {

    @Autowired
    private AdminSysMenuBiz biz;

    /**
     * 菜单信息列出信息接口
     */
    @ApiOperation(value = "菜单信息列出信息接口", notes = "菜单信息列出信息")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result<List<AdminSysMenuResp>> list(@RequestBody AdminSysMenuListReq sysMenuListReq) {
        return biz.list(sysMenuListReq);
    }

    /**
     * 菜单信息保存信息接口
     */
    @ApiOperation(value = "菜单信息保存接口", notes = "菜单信息保存接口")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<Integer> save(@RequestBody AdminSysMenuSaveReq sysMenuSaveReq) {
        return biz.save(sysMenuSaveReq);
    }

    /**
     * 菜单信息删除接口
     */
    @ApiOperation(value = "菜单信息删除接口", notes = "菜单信息删除接口")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result<Integer> delete(@RequestBody AdminSysMenuDeleteReq sysMenuDeleteReq) {
        return biz.delete(sysMenuDeleteReq);
    }

    /**
     * 菜单信息更新接口
     */
    @ApiOperation(value = "菜单信息更新接口", notes = "菜单信息更新接口")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result<Integer> update(@RequestBody AdminSysMenuUpdateReq sysMenuUpdateReq) {
        return biz.update(sysMenuUpdateReq);
    }

    /**
     * 菜单信息查看接口
     */
    @ApiOperation(value = "菜单信息查看接口", notes = "菜单信息查看接口")
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public Result<AdminSysMenuViewResp> view(@RequestBody AdminSysMenuViewReq sysMenuViewREQ) {
        return biz.view(sysMenuViewREQ);
    }

    /**
     * 用户菜单信息列出信息接口
     */
    @ApiOperation(value = "登录用户菜单接口", notes = "获取当前登录用户的所有菜单")
    @RequestMapping(value = "/user/list", method = RequestMethod.POST)
    public Result<List<AdminSysMenuUserResp>> userList() {
        return biz.userList();
    }

    /**
     * 用户菜单信息列出信息接口
     */
    @ApiOperation(value = "指定用户菜单接口", notes = "获取指定用户的所有菜单")
    @RequestMapping(value = "/button/list", method = RequestMethod.POST)
    public Result<List<AdminSysMenuUserResp>> buttonList(@RequestBody AdminSysMenuUserListReq sysMenuUserListREQ) {
        return biz.buttonList(sysMenuUserListREQ);
    }

}
