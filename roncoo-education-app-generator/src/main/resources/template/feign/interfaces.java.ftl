package ${cfg.packagePrefix}.${cfg.packageName!}.feign.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ${cfg.packagePrefix}.common.core.base.Page;
import ${cfg.packagePrefix}.${cfg.packageName!}.feign.qo.${entity}QO;
import ${cfg.packagePrefix}.${cfg.packageName!}.feign.vo.${entity}VO;

/**
* ${table.comment} 接口
*
* @author ${author}
* @date ${date}
*/
@FeignClient(value = "service-${cfg.packageName!}")
public interface IFeign${entity}{

@RequestMapping(value = "/${cfg.packageName!}/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>/listForPage", method = RequestMethod.POST)
Page
<${entity}VO> listForPage(@RequestBody ${entity}QO qo);

    @RequestMapping(value = "/${cfg.packageName!}/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>/save", method = RequestMethod.POST)
    int save(@RequestBody ${entity}QO qo);

    @RequestMapping(value = "/${cfg.packageName!}/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>/delete/{id}", method = RequestMethod.DELETE)
    int deleteById(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/${cfg.packageName!}/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>/updateById", method = RequestMethod.PUT)
    int updateById(@RequestBody ${entity}QO qo);

    @RequestMapping(value = "/${cfg.packageName!}/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>/get/{id}", method = RequestMethod.GET)
    ${entity}VO getById(@PathVariable(value = "id") Long id);

    }
