package ${cfg.packagePrefix}.${cfg.packageName!}.feign;

import ${cfg.packagePrefix}.common.core.base.Page;
import ${cfg.packagePrefix}.common.service.BaseController;
import ${cfg.packagePrefix}.${cfg.packageName!}.feign.biz.Feign${entity}Biz;
import ${cfg.packagePrefix}.${cfg.packageName!}.feign.interfaces.IFeign${entity};
import ${cfg.packagePrefix}.${cfg.packageName!}.feign.interfaces.qo.${entity}EditQO;
import ${cfg.packagePrefix}.${cfg.packageName!}.feign.interfaces.qo.${entity}PageQO;
import ${cfg.packagePrefix}.${cfg.packageName!}.feign.interfaces.qo.${entity}SaveQO;
import ${cfg.packagePrefix}.${cfg.packageName!}.feign.interfaces.vo.${entity}PageVO;
import ${cfg.packagePrefix}.${cfg.packageName!}.feign.interfaces.vo.${entity}ViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * ${table.comment!}
 *
 * @author ${author}
 * @date ${date}
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/${cfg.packageName!}/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.name?replace('_', "/")}</#if>")
public class Feign${entity}Controller extends BaseController implements IFeign${entity} {

    @NotNull
    private final Feign${entity}Biz biz;

    @Override
    public Page<${entity}PageVO> page(@RequestBody ${entity}PageQO qo) {
        return biz.page(qo);
    }

    @Override
    public int save(@RequestBody ${entity}SaveQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody ${entity}EditQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public ${entity}ViewVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }
}
