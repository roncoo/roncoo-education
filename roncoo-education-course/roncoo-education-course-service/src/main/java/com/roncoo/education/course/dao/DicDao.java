package com.roncoo.education.course.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.dao.impl.mapper.entity.Dic;
import com.roncoo.education.course.dao.impl.mapper.entity.DicExample;

public interface DicDao {
    int save(Dic record);

    int deleteById(Long id);

    int updateById(Dic record);

    Dic getById(Long id);

    Page<Dic> listForPage(int pageCurrent, int pageSize, DicExample example);
}
