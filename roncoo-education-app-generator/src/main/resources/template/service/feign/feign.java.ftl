package ${cfg.packagePrefix}.${cfg.packageName!}.service.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ${cfg.packagePrefix}.common.core.base.BaseController;
import ${cfg.packagePrefix}.common.core.base.Page;
import ${cfg.packagePrefix}.${cfg.packageName!}.feign.interfaces.IFeign${entity};
import ${cfg.packagePrefix}.${cfg.packageName!}.feign.qo.${entity}QO;
import ${cfg.packagePrefix}.${cfg.packageName!}.feign.vo.${entity}VO;
import ${cfg.packagePrefix}.${cfg.packageName!}.service.feign.biz.Feign${entity}Biz;

/**
* ${table.comment!}
*
* @author ${author}
* @date ${date}
*/
@RestController
public class Feign${entity}Controller extends BaseController implements IFeign${entity}{

@Autowired
private Feign${entity}Biz biz;

@Override
public Page
<${entity}VO> listForPage(@RequestBody ${entity}QO qo) {
    return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody ${entity}QO qo) {
    return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
    return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody ${entity}QO qo) {
    return biz.updateById(qo);
    }

    @Override
    public ${entity}VO getById(@PathVariable(value = "id") Long id) {
    return biz.getById(id);
    }
    }
