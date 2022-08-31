package ${cfg.packagePrefix}.${cfg.packageName!}.service.admin;

import ${cfg.packagePrefix}.common.core.base.Page;
import ${cfg.packagePrefix}.common.core.base.Result;
import ${cfg.packagePrefix}.${cfg.packageName!}.service.admin.biz.Admin${entity}Biz;
import ${cfg.packagePrefix}.${cfg.packageName!}.service.admin.req.Admin${entity}EditReq;
import ${cfg.packagePrefix}.${cfg.packageName!}.service.admin.req.Admin${entity}PageReq;
import ${cfg.packagePrefix}.${cfg.packageName!}.service.admin.req.Admin${entity}SaveReq;
import ${cfg.packagePrefix}.${cfg.packageName!}.service.admin.resp.Admin${entity}PageResp;
import ${cfg.packagePrefix}.${cfg.packageName!}.service.admin.resp.Admin${entity}ViewResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * ADMIN-${table.comment!}
 *
 * @author ${author}
 */
@Api(tags = "ADMIN-${table.comment!}")
@RestController
@RequiredArgsConstructor
@RequestMapping("/${cfg.packageName}/admin/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.name ?replace("_","/")}</#if>")
public class Admin${table.entityName}Controller {

    @NotNull
    private final Admin${entity}Biz biz;

    @ApiOperation(value = "${table.comment!}分页", notes = "${table.comment!}分页")
    @PostMapping(value = "/page")
    public Result<Page<#noparse><</#noparse>Admin${entity}PageResp<#noparse>></#noparse>> page(@RequestBody Admin${entity}PageReq req) {
        return biz.page(req);
    }

    @ApiOperation(value = "${table.comment!}添加", notes = "${table.comment!}添加")
    @PostMapping(value = "/save")
    public Result<String> save(@RequestBody @Valid Admin${entity}SaveReq req) {
        return biz.save(req);
    }

    @ApiOperation(value = "${table.comment!}查看", notes = "${table.comment!}查看")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @GetMapping(value = "/view")
    public Result<Admin${entity}ViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

    @ApiOperation(value = "${table.comment!}修改", notes = "${table.comment!}修改")
    @PutMapping(value = "/edit")
    public Result<String> edit(@RequestBody @Valid Admin${entity}EditReq req) {
        return biz.edit(req);
    }

    @ApiOperation(value = "${table.comment!}删除", notes = "${table.comment!}删除")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam Long id) {
        return biz.delete(id);
    }
}
