package ${cfg.packagePrefix}.${cfg.packageName!}.dao;

import ${cfg.packagePrefix}.common.core.base.Page;
import ${cfg.packagePrefix}.${cfg.packageName!}.dao.impl.mapper.entity.${entity};
import ${cfg.packagePrefix}.${cfg.packageName!}.dao.impl.mapper.entity.${entity}Example;

import java.util.List;

/**
 * ${table.comment!} 服务类
 *
 * @author ${author}
 * @date ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${entity}Dao {

    /**
     * 保存${table.comment!}
     *
     * @param record ${table.comment!}
     * @return 影响记录数
     */
    int save(${entity} record);

    /**
     * 根据ID删除${table.comment!}
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    int deleteById(Long id);

    /**
     * 修改${table.comment!}
     *
     * @param record ${table.comment!}
     * @return 影响记录数
     */
    int updateById(${entity} record);

    /**
     * 根据ID获取${table.comment!}
     *
     * @param id 主键ID
     * @return ${table.comment!}
     */
    ${entity} getById(Long id);

    /**
     * ${table.comment!}--分页查询
     *
     * @param pageCurrent 当前页
     * @param pageSize    分页大小
     * @param example     查询条件
     * @return 分页结果
     */
    Page<${entity}> page(int pageCurrent, int pageSize, ${entity}Example example);

    /**
     * ${table.comment!}--条件列出
     *
     * @param example     查询条件
     * @return ${table.comment!}列表
     */
    List<${entity}> listByExample(${entity}Example example);

    /**
     * ${table.comment!}--条件统计
     *
     * @param example     统计条件
     * @return ${table.comment!}数量
     */
    int countByExample(${entity}Example example);
}
</#if>
