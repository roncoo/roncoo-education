package ${cfg.packagePrefix}.${cfg.packageName!}.service.feign.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ${cfg.packagePrefix}.common.core.base.BaseBiz;
import ${cfg.packagePrefix}.common.core.base.Page;
import ${cfg.packagePrefix}.common.core.base.PageUtil;
import ${cfg.packagePrefix}.common.core.tools.BeanUtil;
import ${cfg.packagePrefix}.${cfg.packageName!}.feign.qo.${entity}QO;
import ${cfg.packagePrefix}.${cfg.packageName!}.feign.vo.${entity}VO;
import ${cfg.packagePrefix}.${cfg.packageName!}.service.dao.${entity}Dao;
import ${cfg.packagePrefix}.${cfg.packageName!}.service.dao.impl.mapper.entity.${entity};
import ${cfg.packagePrefix}.${cfg.packageName!}.service.dao.impl.mapper.entity.${entity}Example;
import ${cfg.packagePrefix}.${cfg.packageName!}.service.dao.impl.mapper.entity.${entity}Example.Criteria;

/**
* ${table.comment!}
*
* @author ${author}
*/
@Component
public class Feign${entity}Biz extends BaseBiz {

@Autowired
private ${entity}Dao dao;

public Page
<${entity}VO> listForPage(${entity}QO qo) {
    ${entity}Example example = new ${entity}Example();
    Criteria c = example.createCriteria();
    example.setOrderByClause(" id desc ");
    Page<${entity}> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
    return PageUtil.transform(page, ${entity}VO.class);
    }

    public int save(${entity}QO qo) {
    ${entity} record = BeanUtil.copyProperties(qo, ${entity}.class);
    return dao.save(record);
    }

    public int deleteById(Long id) {
    return dao.deleteById(id);
    }

    public ${entity}VO getById(Long id) {
    ${entity} record = dao.getById(id);
    return BeanUtil.copyProperties(record, ${entity}VO.class);
    }

    public int updateById(${entity}QO qo) {
    ${entity} record = BeanUtil.copyProperties(qo, ${entity}.class);
    return dao.updateById(record);
    }

    }
