package com.roncoo.education.course.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.dao.impl.mapper.entity.DicList;
import com.roncoo.education.course.dao.impl.mapper.entity.DicListExample;

public interface DicListDao {
    int save(DicList record);

    int deleteById(Long id);

    int updateById(DicList record);

    DicList getById(Long id);

    Page<DicList> listForPage(int pageCurrent, int pageSize, DicListExample example);
}
