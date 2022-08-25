package ${cfg.packagePrefix}.${cfg.packageName!}.service.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ${cfg.packagePrefix}.common.core.base.Page;
import ${cfg.packagePrefix}.common.core.base.PageUtil;
import ${cfg.packagePrefix}.common.core.tools.IdWorker;
import ${cfg.packagePrefix}.${cfg.packageName!}.service.dao.${entity}Dao;
import ${cfg.packagePrefix}.${cfg.packageName!}.service.dao.impl.mapper.${entity}Mapper;
import ${cfg.packagePrefix}.${cfg.packageName!}.service.dao.impl.mapper.entity.${entity};
import ${cfg.packagePrefix}.${cfg.packageName!}.service.dao.impl.mapper.entity.${entity}Example;

/**
* ${table.comment!} 服务实现类
*
* @author ${author}
* @date ${date}
*/
@Repository
public class ${entity}DaoImpl implements ${entity}Dao {

@Autowired
private ${entity}Mapper mapper;

@Override
public int save(${entity} record) {
record.setId(IdWorker.getId());
return this.mapper.insertSelective(record);
}

@Override
public int deleteById(Long id) {
return this.mapper.deleteByPrimaryKey(id);
}

@Override
public int updateById(${entity} record) {
return this.mapper.updateByPrimaryKeySelective(record);
}

@Override
public ${entity} getById(Long id) {
return this.mapper.selectByPrimaryKey(id);
}

@Override
public Page<${entity}> listForPage(int pageCurrent, int pageSize, ${entity}Example example) {
int count = this.mapper.countByExample(example);
pageSize = PageUtil.checkPageSize(pageSize);
pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
int totalPage = PageUtil.countTotalPage(count, pageSize);
example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
example.setPageSize(pageSize);
return new Page<>(count, totalPage, pageCurrent, pageSize, this.mapper.selectByExample(example));
}


}
