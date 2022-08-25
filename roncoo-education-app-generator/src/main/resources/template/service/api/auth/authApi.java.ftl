package ${cfg.packagePrefix}.${cfg.packageName!}.service.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;

import ${cfg.packagePrefix}.${cfg.packageName!}.service.api.auth.biz.Auth${entity}Biz;

/**
* ${table.comment!} UserApi接口
*
* @author ${author}
* @date ${date}
*/
@Api(tags = "API-AUTH-${table.comment!}")
@RestController
@RequestMapping("/${cfg.packageName}/auth/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.name ?replace("_","/")}</#if>")
public class Auth${table.entityName}Controller {

@Autowired
private Auth${entity}Biz biz;

}
