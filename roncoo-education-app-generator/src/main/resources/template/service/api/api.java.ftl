package ${cfg.packagePrefix}.${cfg.packageName!}.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;

import ${cfg.packagePrefix}.${cfg.packageName!}.service.api.biz.Api${entity}Biz;
/**
* ${table.comment!} Api接口
*
* @author ${author}
* @date ${date}
*/
@Api(tags = "API-${table.comment!}")
@RestController
@RequestMapping("/${cfg.packageName}/api/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.name ?replace("_","/")}</#if>")
public class Api${table.entityName}Controller {

@Autowired
private Api${entity}Biz biz;

}
