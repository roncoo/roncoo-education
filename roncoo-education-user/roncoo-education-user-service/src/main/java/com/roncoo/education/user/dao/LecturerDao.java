package com.roncoo.education.user.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.dao.impl.mapper.entity.Lecturer;
import com.roncoo.education.user.dao.impl.mapper.entity.LecturerExample;

import java.util.List;

public interface LecturerDao {
    int save(Lecturer record);

    int deleteById(Long id);

    int updateById(Lecturer record);

    Lecturer getById(Long id);

    Page<Lecturer> page(int pageCurrent, int pageSize, LecturerExample example);

    /**
     * 根据讲师名称查找讲师信息
     *
     * @param LecturerName
     * @return
     * @author WY
     */
    List<Lecturer> getByLecturerName(String LecturerName);

    List<Lecturer> listByStatusId(Integer statusId);

    /**
     * 根据讲师编号集合获取讲师信息
     *
     * @param lectureruserIds
     * @return
     */
    List<Lecturer> listByIds(List<Long> ids);
}
