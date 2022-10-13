package ${cfg.packagePrefix}.${cfg.packageName!}.feign.biz;


import ${cfg.packagePrefix}.common.core.base.Page;
import ${cfg.packagePrefix}.common.core.base.PageUtil;
import ${cfg.packagePrefix}.common.core.tools.BeanUtil;
import ${cfg.packagePrefix}.common.service.BaseBiz;
import ${cfg.packagePrefix}.${cfg.packageName!}.dao.${entity}Dao;
import ${cfg.packagePrefix}.${cfg.packageName!}.dao.impl.mapper.entity.${entity};
import ${cfg.packagePrefix}.${cfg.packageName!}.dao.impl.mapper.entity.${entity}Example;
import ${cfg.packagePrefix}.${cfg.packageName!}.dao.impl.mapper.entity.${entity}Example.Criteria;
import ${cfg.packagePrefix}.${cfg.packageName!}.feign.interfaces.qo.${entity}EditQO;
import ${cfg.packagePrefix}.${cfg.packageName!}.feign.interfaces.qo.${entity}PageQO;
import ${cfg.packagePrefix}.${cfg.packageName!}.feign.interfaces.qo.${entity}SaveQO;
import ${cfg.packagePrefix}.${cfg.packageName!}.feign.interfaces.vo.${entity}PageVO;
import ${cfg.packagePrefix}.${cfg.packageName!}.feign.interfaces.vo.${entity}ViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * ${table.comment!}
 *
 * @author ${author}
 */
@Component
@RequiredArgsConstructor
public class Feign${entity}Biz extends BaseBiz {

    @NotNull
    private final ${entity}Dao dao;

    public Page<${entity}PageVO> page(${entity}PageQO qo) {
        ${entity}Example example = new ${entity}Example();
        Criteria c = example.createCriteria();
        example.setOrderByClause(" id desc ");
        Page<${entity}> page = dao.page(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, ${entity}PageVO.class);
    }

    public int save(${entity}SaveQO qo) {
        ${entity} record = BeanUtil.copyProperties(qo, ${entity}.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public int updateById(${entity}EditQO qo) {
        ${entity} record = BeanUtil.copyProperties(qo, ${entity}.class);
        return dao.updateById(record);
    }

    public ${entity}ViewVO getById(Long id) {
        ${entity} record = dao.getById(id);
        return BeanUtil.copyProperties(record, ${entity}ViewVO.class);
    }
}
