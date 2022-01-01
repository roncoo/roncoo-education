package com.roncoo.education.user.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.dao.impl.mapper.entity.LecturerProfit;
import com.roncoo.education.user.dao.impl.mapper.entity.LecturerProfitExample;

public interface LecturerProfitDao {
    int save(LecturerProfit record);

    int deleteById(Long id);

    int updateById(LecturerProfit record);

    LecturerProfit getById(Long id);

    Page<LecturerProfit> listForPage(int pageCurrent, int pageSize, LecturerProfitExample example);
}
