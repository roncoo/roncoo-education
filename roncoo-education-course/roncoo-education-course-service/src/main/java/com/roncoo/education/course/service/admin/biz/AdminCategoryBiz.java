package com.roncoo.education.course.service.admin.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.dao.CategoryDao;
import com.roncoo.education.course.dao.impl.mapper.entity.Category;
import com.roncoo.education.course.dao.impl.mapper.entity.CategoryExample;
import com.roncoo.education.course.dao.impl.mapper.entity.CategoryExample.Criteria;
import com.roncoo.education.course.service.admin.req.AdminCategoryEditReq;
import com.roncoo.education.course.service.admin.req.AdminCategoryPageReq;
import com.roncoo.education.course.service.admin.req.AdminCategorySaveReq;
import com.roncoo.education.course.service.admin.resp.AdminCategoryPageResp;
import com.roncoo.education.course.service.admin.resp.AdminCategoryViewResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * ADMIN-分类
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AdminCategoryBiz extends BaseBiz {

    @NotNull
    private final CategoryDao dao;

    /**
     * 分类分页
     *
     * @param req 分类分页查询参数
     * @return 分类分页查询结果
     */
    public Result<Page<AdminCategoryPageResp>> page(AdminCategoryPageReq req) {
        CategoryExample example = new CategoryExample();
        Criteria c = example.createCriteria();
        Page<Category> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminCategoryPageResp> respPage = PageUtil.transform(page, AdminCategoryPageResp.class);
        return Result.success(respPage);
    }

    /**
     * 分类添加
     *
     * @param req 分类
     * @return 添加结果
     */
    public Result<String> save(AdminCategorySaveReq req) {
        Category record = BeanUtil.copyProperties(req, Category.class);
        if (dao.save(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 分类查看
     *
     * @param id 主键ID
     * @return 分类
     */
    public Result<AdminCategoryViewResp> view(Long id) {
        return Result.success(BeanUtil.copyProperties(dao.getById(id), AdminCategoryViewResp.class));
    }

    /**
     * 分类修改
     *
     * @param req 分类修改对象
     * @return 修改结果
     */
    public Result<String> edit(AdminCategoryEditReq req) {
        Category record = BeanUtil.copyProperties(req, Category.class);
        if (dao.updateById(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 分类删除
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
