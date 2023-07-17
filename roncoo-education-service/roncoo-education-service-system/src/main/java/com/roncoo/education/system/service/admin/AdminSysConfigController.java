package com.roncoo.education.system.service.admin;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.service.admin.biz.AdminSysConfigBiz;
import com.roncoo.education.system.service.admin.req.AdminSysConfigEditReq;
import com.roncoo.education.system.service.admin.req.AdminSysConfigListReq;
import com.roncoo.education.system.service.admin.req.AdminSysConfigPageReq;
import com.roncoo.education.system.service.admin.req.AdminSysConfigSaveReq;
import com.roncoo.education.system.service.admin.resp.AdminSysConfigListResp;
import com.roncoo.education.system.service.admin.resp.AdminSysConfigPageResp;
import com.roncoo.education.system.service.admin.resp.AdminSysConfigViewResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * ADMIN-系统配置
 *
 * @author wujing
 */
@Api(tags = "admin-系统配置")
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/admin/sys/config")
public class AdminSysConfigController {

    @NotNull
    private final AdminSysConfigBiz biz;

    @ApiOperation(value = "系统配置列表", notes = "系统配置列表")
    @PostMapping(value = "/list")
    public Result<List<AdminSysConfigListResp>> list(@RequestBody AdminSysConfigListReq req) {
        return biz.list(req);
    }

    @ApiOperation(value = "系统配置分页", notes = "系统配置分页")
    @PostMapping(value = "/page")
    public Result<Page<AdminSysConfigPageResp>> page(@RequestBody AdminSysConfigPageReq req) {
        return biz.page(req);
    }

    @ApiOperation(value = "系统配置添加", notes = "系统配置添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid AdminSysConfigSaveReq req) {
        return biz.save(req);
    }

    @ApiOperation(value = "系统配置查看", notes = "系统配置查看")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @GetMapping(value = "/view")
    public Result<AdminSysConfigViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @ApiOperation(value = "系统配置修改", notes = "系统配置修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid AdminSysConfigEditReq req) {
        return biz.edit(req);
    }

    @ApiOperation(value = "系统配置删除", notes = "系统配置删除")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }

    @ApiOperation(value = "视频云初始化设置", notes = "视频云初始化设置")
    @GetMapping(value = "/video/init")
    public Result<String> videoInit() {
        return biz.init();
    }
}
