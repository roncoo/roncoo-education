package com.roncoo.education.course.dao.impl;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.IdWorker;
import com.roncoo.education.course.dao.DicListDao;
import com.roncoo.education.course.dao.impl.mapper.DicListMapper;
import com.roncoo.education.course.dao.impl.mapper.entity.DicList;
import com.roncoo.education.course.dao.impl.mapper.entity.DicListExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DicListDaoImpl implements DicListDao {
    @Autowired
    private DicListMapper dicListMapper;

    public int save(DicList record) {
        record.setId(IdWorker.getId());
        return this.dicListMapper.insertSelective(record);
    }

    public int deleteById(Long id) {
        return this.dicListMapper.deleteByPrimaryKey(id);
    }

    public int updateById(DicList record) {
        return this.dicListMapper.updateByPrimaryKeySelective(record);
    }

    public DicList getById(Long id) {
        return this.dicListMapper.selectByPrimaryKey(id);
    }

    public Page<DicList> listForPage(int pageCurrent, int pageSize, DicListExample example) {
        int count = this.dicListMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<DicList>(count, totalPage, pageCurrent, pageSize, this.dicListMapper.selectByExample(example));
    }
}
