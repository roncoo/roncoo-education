package ${cfg.packagePrefix}.${cfg.packageName!}.service.api;

import ${cfg.packagePrefix}.${cfg.packageName!}.service.api.biz.Api${entity}Biz;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * API-${table.comment!}
 *
 * @author ${author}
 * @date ${date}
 */
@Api(tags = "API-${table.comment!}")
@RestController
@RequiredArgsConstructor
@RequestMapping("/${cfg.packageName}/api/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.name?replace('_', "/")}</#if>")
public class Api${table.entityName}Controller {

    @NotNull
    private final Api${entity}Biz biz;

}
