package ${cfg.packagePrefix}.${cfg.packageName!}.service.auth;

import ${cfg.packagePrefix}.${cfg.packageName!}.service.auth.biz.Auth${entity}Biz;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * AUTH-${table.comment!}
 *
 * @author ${author}
 * @date ${date}
 */
@Api(tags = "AUTH-${table.comment!}")
@RestController
@RequiredArgsConstructor
@RequestMapping("/${cfg.packageName}/auth/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.name?replace('_', "/")}</#if>")
public class Auth${table.entityName}Controller {

    @NotNull
    private final Auth${entity}Biz biz;

}
