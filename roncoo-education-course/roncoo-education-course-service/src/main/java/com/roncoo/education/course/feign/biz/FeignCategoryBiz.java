package com.roncoo.education.course.feign.biz;


import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.dao.CategoryDao;
import com.roncoo.education.course.dao.impl.mapper.entity.Category;
import com.roncoo.education.course.dao.impl.mapper.entity.CategoryExample;
import com.roncoo.education.course.dao.impl.mapper.entity.CategoryExample.Criteria;
import com.roncoo.education.course.feign.interfaces.qo.CategoryEditQO;
import com.roncoo.education.course.feign.interfaces.qo.CategoryPageQO;
import com.roncoo.education.course.feign.interfaces.qo.CategorySaveQO;
import com.roncoo.education.course.feign.interfaces.vo.CategoryPageVO;
import com.roncoo.education.course.feign.interfaces.vo.CategoryViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 分类
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class FeignCategoryBiz extends BaseBiz {

    @NotNull
    private final CategoryDao dao;

    public Page<CategoryPageVO> page(CategoryPageQO qo) {
        CategoryExample example = new CategoryExample();
        Criteria c = example.createCriteria();
        example.setOrderByClause(" id desc ");
        Page<Category> page = dao.page(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, CategoryPageVO.class);
    }

    public int save(CategorySaveQO qo) {
        Category record = BeanUtil.copyProperties(qo, Category.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public int updateById(CategoryEditQO qo) {
        Category record = BeanUtil.copyProperties(qo, Category.class);
        return dao.updateById(record);
    }

    public CategoryViewVO getById(Long id) {
        Category record = dao.getById(id);
        return BeanUtil.copyProperties(record, CategoryViewVO.class);
    }
}
