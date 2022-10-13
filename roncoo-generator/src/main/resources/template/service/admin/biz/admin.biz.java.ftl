package ${cfg.packagePrefix}.${cfg.packageName!}.service.admin.biz;

import ${cfg.packagePrefix}.common.service.BaseBiz;
import ${cfg.packagePrefix}.common.core.base.Page;
import ${cfg.packagePrefix}.common.core.base.PageUtil;
import ${cfg.packagePrefix}.common.core.base.Result;
import ${cfg.packagePrefix}.common.core.tools.BeanUtil;
import ${cfg.packagePrefix}.${cfg.packageName!}.service.admin.req.Admin${entity}EditReq;
import ${cfg.packagePrefix}.${cfg.packageName!}.service.admin.req.Admin${entity}PageReq;
import ${cfg.packagePrefix}.${cfg.packageName!}.service.admin.req.Admin${entity}SaveReq;
import ${cfg.packagePrefix}.${cfg.packageName!}.service.admin.resp.Admin${entity}PageResp;
import ${cfg.packagePrefix}.${cfg.packageName!}.service.admin.resp.Admin${entity}ViewResp;
import ${cfg.packagePrefix}.${cfg.packageName!}.dao.${entity}Dao;
import ${cfg.packagePrefix}.${cfg.packageName!}.dao.impl.mapper.entity.${entity};
import ${cfg.packagePrefix}.${cfg.packageName!}.dao.impl.mapper.entity.${entity}Example;
import ${cfg.packagePrefix}.${cfg.packageName!}.dao.impl.mapper.entity.${entity}Example.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * ADMIN-${table.comment!}
 *
 * @author ${author}
 */
@Component
@RequiredArgsConstructor
public class Admin${entity}Biz extends BaseBiz {

    @NotNull
    private final ${entity}Dao dao;

    /**
     * ${table.comment!}分页
     *
     * @param req ${table.comment!}分页查询参数
     * @return ${table.comment!}分页查询结果
     */
    public Result<Page<#noparse><</#noparse>Admin${entity}PageResp<#noparse>></#noparse>> page(Admin${entity}PageReq req) {
        ${entity}Example example = new ${entity}Example();
        Criteria c = example.createCriteria();
        Page<${entity}> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<Admin${entity}PageResp> respPage = PageUtil.transform(page, Admin${entity}PageResp.class);
        return Result.success(respPage);
    }

    /**
     * ${table.comment!}添加
     *
     * @param req ${table.comment!}
     * @return 添加结果
     */
    public Result<String> save(Admin${entity}SaveReq req) {
        ${entity} record = BeanUtil.copyProperties(req, ${entity}.class);
        if (dao.save(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * ${table.comment!}查看
     *
     * @param id 主键ID
     * @return ${table.comment!}
     */
    public Result<Admin${entity}ViewResp> view(Long id) {
        return Result.success(BeanUtil.copyProperties(dao.getById(id), Admin${entity}ViewResp.class));
    }

    /**
     * ${table.comment!}修改
     *
     * @param req ${table.comment!}修改对象
     * @return 修改结果
     */
    public Result<String> edit(Admin${entity}EditReq req) {
        ${entity} record = BeanUtil.copyProperties(req, ${entity}.class);
        if (dao.updateById(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * ${table.comment!}删除
     *
     * @param id ID主键
     * @return 删除结果
     */
    public Result<String> delete(Long id) {
        if (dao.deleteById(id) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }
}
