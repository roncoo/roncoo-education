package com.roncoo.education.course.dao.impl;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.IdWorker;
import com.roncoo.education.course.dao.CategoryDao;
import com.roncoo.education.course.dao.impl.mapper.CategoryMapper;
import com.roncoo.education.course.dao.impl.mapper.entity.Category;
import com.roncoo.education.course.dao.impl.mapper.entity.CategoryExample;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 分类 服务实现类
 *
 * @author wujing
 * @date 2022-08-25
 */
@Repository
@RequiredArgsConstructor
public class CategoryDaoImpl implements CategoryDao {

    @NotNull
    private final CategoryMapper mapper;

    @Override
    public int save(Category record) {
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
    public int updateById(Category record) {
        record.setGmtCreate(null);
        record.setGmtModified(null);
        return this.mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Category getById(Long id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<Category> page(int pageCurrent, int pageSize, CategoryExample example) {
        int count = this.mapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<>(count, totalPage, pageCurrent, pageSize, this.mapper.selectByExample(example));
    }

    @Override
    public List<Category> listByExample(CategoryExample example) {
        return this.mapper.selectByExample(example);
    }

    @Override
    public int countByExample(CategoryExample example) {
        return this.mapper.countByExample(example);
    }

    @Override
    public List<Category> listByIds(List<Long> categoryIdList) {
        CategoryExample example = new CategoryExample();
        example.createCriteria().andIdIn(categoryIdList);
        return this.mapper.selectByExample(example);
    }
}
