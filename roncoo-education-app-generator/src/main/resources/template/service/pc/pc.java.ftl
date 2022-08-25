package ${cfg.packagePrefix}.${cfg.packageName!}.service.pc;

import ${cfg.packagePrefix}.common.core.base.Page;
import ${cfg.packagePrefix}.common.core.base.Result;
import ${cfg.packagePrefix}.${cfg.packageName!}.common.req.${entity}EditREQ;
import ${cfg.packagePrefix}.${cfg.packageName!}.common.req.${entity}ListREQ;
import ${cfg.packagePrefix}.${cfg.packageName!}.common.req.${entity}SaveREQ;
import ${cfg.packagePrefix}.${cfg.packageName!}.common.resp.${entity}ListRESP;
import ${cfg.packagePrefix}.${cfg.packageName!}.common.resp.${entity}ViewRESP;
import ${cfg.packagePrefix}.${cfg.packageName!}.service.pc.biz.Pc${entity}Biz;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
* ${table.comment!} Pc接口
*
* @author ${author}
*/
@Api(tags = "PC-${table.comment!}")
@RestController
@RequestMapping("/${cfg.packageName}/pc/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.name ?replace("_","/")}</#if>")
public class Pc${table.entityName}Controller {

@Autowired
private Pc${entity}Biz biz;

@ApiOperation(value = "${table.comment!}列表", notes = "${table.comment!}列表")
@PostMapping(value = "/list")
public Result
<Page<#noparse>
<</#noparse>${entity}ListRESP<#noparse>></#noparse>> list(@RequestBody ${entity}ListREQ ${entity ?uncap_first}ListREQ) {
    return biz.list(${entity ?uncap_first}ListREQ);
    }

    @ApiOperation(value = "${table.comment!}添加", notes = "${table.comment!}添加")
    @PostMapping(value = "/save")
    public Result
    <String> save(@RequestBody ${entity}SaveREQ ${entity ?uncap_first}SaveREQ) {
        return biz.save(${entity ?uncap_first}SaveREQ);
        }

        @ApiOperation(value = "${table.comment!}查看", notes = "${table.comment!}查看")
        @GetMapping(value = "/view")
        public Result
        <${entity}ViewRESP> view(@RequestParam Long id) {
            return biz.view(id);
            }

            @ApiOperation(value = "${table.comment!}修改", notes = "${table.comment!}修改")
            @PutMapping(value = "/edit")
            public Result
            <String> edit(@RequestBody ${entity}EditREQ ${entity ?uncap_first}EditREQ) {
                return biz.edit(${entity ?uncap_first}EditREQ);
                }

                @ApiOperation(value = "${table.comment!}删除", notes = "${table.comment!}删除")
                @DeleteMapping(value = "/delete")
                public Result
                <String> delete(@RequestParam Long id) {
                    return biz.delete(id);
                    }
                    }
