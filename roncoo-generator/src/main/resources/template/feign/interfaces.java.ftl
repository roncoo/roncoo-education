package ${cfg.packagePrefix}.${cfg.packageName!}.feign.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import ${cfg.packagePrefix}.common.core.base.Page;
import ${cfg.packagePrefix}.${cfg.packageName!}.feign.interfaces.qo.${entity}PageQO;
import ${cfg.packagePrefix}.${cfg.packageName!}.feign.interfaces.qo.${entity}SaveQO;
import ${cfg.packagePrefix}.${cfg.packageName!}.feign.interfaces.qo.${entity}EditQO;
import ${cfg.packagePrefix}.${cfg.packageName!}.feign.interfaces.vo.${entity}PageVO;
import ${cfg.packagePrefix}.${cfg.packageName!}.feign.interfaces.vo.${entity}ViewVO;

/**
 * ${table.comment} 接口
 *
 * @author ${author}
 * @date ${date}
 */
@FeignClient(value = "${cfg.projectName!?replace("roncoo-education-", "")}", path = "/${cfg.packageName!}/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.name?replace('_', "/")}</#if>")
public interface IFeign${entity} {

    /**
     * 分页列出-${table.comment!}
     *
     * @param qo ${table.comment!}
     * @return 分页结果
     */
    @PostMapping(value = "/page")
    Page<${entity}PageVO> page(@RequestBody ${entity}PageQO qo);

    /**
     * 保存-${table.comment!}
     *
     * @param qo ${table.comment!}
     * @return 影响记录数
     */
    @PostMapping(value = "/save")
    int save(@RequestBody ${entity}SaveQO qo);

    /**
     * 根据ID删除-${table.comment!}
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    @DeleteMapping(value = "/delete/{id}")
    int deleteById(@PathVariable(value = "id") Long id);

    /**
     * 修改${table.comment!}
     *
     * @param qo ${table.comment!}
     * @return 影响记录数
     */
    @PutMapping(value = "/edit")
    int updateById(@RequestBody ${entity}EditQO qo);

    /**
     * 根据ID获取${table.comment!}
     *
     * @param id 主键ID
     * @return ${table.comment!}
     */
    @GetMapping(value = "/get/{id}")
    ${entity}ViewVO getById(@PathVariable(value = "id") Long id);

}
