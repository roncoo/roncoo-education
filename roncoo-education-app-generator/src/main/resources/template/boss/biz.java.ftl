package ${cfg.packagePrefix}.web.boss.biz.${cfg.packageName!};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ${cfg.packagePrefix}.common.core.base.BaseBiz;
import ${cfg.packagePrefix}.common.core.base.Page;
import ${cfg.packagePrefix}.${cfg.packageName!}.feign.interfaces.IFeign${entity};
import ${cfg.packagePrefix}.${cfg.packageName!}.feign.qo.${entity}QO;
import ${cfg.packagePrefix}.${cfg.packageName!}.feign.vo.${entity}VO;

/**
* ${table.comment}
*
* @author ${author}
*/
@Component
public class ${entity}Biz extends BaseBiz{

@Autowired
private IFeign${entity} feign${entity};

public Page
<${entity}VO> listForPage(${entity}QO qo) {
    return feign${entity}.listForPage(qo);
    }

    public int save(${entity}QO qo) {
    return feign${entity}.save(qo);
    }

    public int deleteById(Long id) {
    return feign${entity}.deleteById(id);
    }

    public ${entity}VO getById(Long id) {
    return feign${entity}.getById(id);
    }

    public int updateById(${entity}QO qo) {
    return feign${entity}.updateById(qo);
    }

    }
