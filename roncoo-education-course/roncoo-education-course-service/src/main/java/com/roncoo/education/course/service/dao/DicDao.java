package com.roncoo.education.course.service.dao;

import com.roncoo.education.course.service.dao.impl.mapper.entity.Dic;
import com.roncoo.education.course.service.dao.impl.mapper.entity.DicExample;
import com.roncoo.education.util.base.Page;

public interface DicDao {
    int save(Dic record);

    int deleteById(Long id);

    int updateById(Dic record);

    Dic getById(Long id);

    Page<Dic> listForPage(int pageCurrent, int pageSize, DicExample example);
}