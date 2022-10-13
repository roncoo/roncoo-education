package ${cfg.packagePrefix}.${cfg.packageName!}.dao.impl;

import ${cfg.packagePrefix}.common.core.base.Page;
import ${cfg.packagePrefix}.common.core.base.PageUtil;
import ${cfg.packagePrefix}.common.core.tools.IdWorker;
import ${cfg.packagePrefix}.${cfg.packageName!}.dao.${entity}Dao;
import ${cfg.packagePrefix}.${cfg.packageName!}.dao.impl.mapper.${entity}Mapper;
import ${cfg.packagePrefix}.${cfg.packageName!}.dao.impl.mapper.entity.${entity};
import ${cfg.packagePrefix}.${cfg.packageName!}.dao.impl.mapper.entity.${entity}Example;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * ${table.comment!} 服务实现类
 *
 * @author ${author}
 * @date ${date}
 */
@Repository
@RequiredArgsConstructor
public class ${entity}DaoImpl implements ${entity}Dao {

    @NotNull
    private final ${entity}Mapper mapper;

    @Override
    public int save(${entity} record) {
        if (record.getId() == null) {
            record.setId(IdWorker.getId());
        }
        return this.mapper.insertSelective(record);
    }

    @Override
    public int deleteById(Long id) {
        return this.mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(${entity} record) {
        <#list table.fields as field>
            <#if field.propertyName == "gmtCreate">
        record.setGmtCreate(null);
            </#if>
            <#if field.propertyName == "gmtModified">
        record.setGmtModified(null);
            </#if>
        </#list>
        return this.mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public ${entity} getById(Long id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<${entity}> page(int pageCurrent, int pageSize, ${entity}Example example) {
        int count = this.mapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<>(count, totalPage, pageCurrent, pageSize, this.mapper.selectByExample(example));
    }

    @Override
    public List<${entity}> listByExample(${entity}Example example) {
        return this.mapper.selectByExample(example);
    }

    @Override
    public int countByExample(${entity}Example example){
        return this.mapper.countByExample(example);
    }
}
