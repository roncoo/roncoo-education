package ${cfg.packagePrefix}.${cfg.packageName!}.service.pc.biz;

import ${cfg.packagePrefix}.common.core.base.BaseBiz;
import ${cfg.packagePrefix}.common.core.base.Page;
import ${cfg.packagePrefix}.common.core.base.PageUtil;
import ${cfg.packagePrefix}.common.core.base.Result;
import ${cfg.packagePrefix}.common.core.tools.BeanUtil;
import ${cfg.packagePrefix}.${cfg.packageName!}.common.req.${entity}EditREQ;
import ${cfg.packagePrefix}.${cfg.packageName!}.common.req.${entity}ListREQ;
import ${cfg.packagePrefix}.${cfg.packageName!}.common.req.${entity}SaveREQ;
import ${cfg.packagePrefix}.${cfg.packageName!}.common.resp.${entity}ListRESP;
import ${cfg.packagePrefix}.${cfg.packageName!}.common.resp.${entity}ViewRESP;
import ${cfg.packagePrefix}.${cfg.packageName!}.service.dao.${entity}Dao;
import ${cfg.packagePrefix}.${cfg.packageName!}.service.dao.impl.mapper.entity.${entity};
import ${cfg.packagePrefix}.${cfg.packageName!}.service.dao.impl.mapper.entity.${entity}Example;
import ${cfg.packagePrefix}.${cfg.packageName!}.service.dao.impl.mapper.entity.${entity}Example.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
* ${table.comment!}
*
* @author ${author}
*/
@Component
public class Pc${entity}Biz extends BaseBiz {

@Autowired
private ${entity}Dao dao;

/**
* ${table.comment!}列表
*
* @param ${entity ?uncap_first}ListREQ ${table.comment!}分页查询参数
* @return ${table.comment!}分页查询结果
*/
public Result
<Page<#noparse>
<</#noparse>${entity}ListRESP<#noparse>></#noparse>> list(${entity}ListREQ ${entity ?uncap_first}ListREQ) {
    ${entity}Example example = new ${entity}Example();
    Criteria c = example.createCriteria();
    Page<${entity}> page = dao.listForPage(${entity ?uncap_first}ListREQ.getPageCurrent(), ${entity ?uncap_first}ListREQ.getPageSize(), example);
    Page
    <${entity}ListRESP> respPage = PageUtil.transform(page, ${entity}ListRESP.class);
        return Result.success(respPage);
        }


        /**
        * ${table.comment!}添加
        *
        * @param ${entity ?uncap_first}SaveREQ ${table.comment!}
        * @return 添加结果
        */
        public Result
        <String> save(${entity}SaveREQ ${entity ?uncap_first}SaveREQ) {
            ${entity} record = BeanUtil.copyProperties(${entity ?uncap_first}SaveREQ, ${entity}.class);
            if (dao.save(record) > 0) {
            return Result.success("添加成功");
            }
            return Result.error("添加失败");
            }


            /**
            * ${table.comment!}查看
            *
            * @param id 主键ID
            * @return ${table.comment!}
            */
            public Result
            <${entity}ViewRESP> view(Long id) {
                return Result.success(BeanUtil.copyProperties(dao.getById(id), ${entity}ViewRESP.class));
                }


                /**
                * ${table.comment!}修改
                *
                * @param ${entity ?uncap_first}EditREQ ${table.comment!}修改对象
                * @return 修改结果
                */
                public Result
                <String> edit(${entity}EditREQ ${entity ?uncap_first}EditREQ) {
                    ${entity} record = BeanUtil.copyProperties(${entity ?uncap_first}EditREQ, ${entity}.class);
                    if (dao.updateById(record) > 0) {
                    return Result.success("编辑成功");
                    }
                    return Result.error("编辑失败");
                    }

                    /**
                    * ${table.comment!}删除
                    *
                    * @param id ID主键
                    * @return 删除结果
                    */
                    public Result
                    <String> delete(Long id) {
                        if (dao.deleteById(id) > 0) {
                        return Result.success("删除成功");
                        }
                        return Result.error("删除失败");
                        }
                        }
