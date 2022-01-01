package com.roncoo.education.course.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.dao.impl.mapper.entity.FileStorage;
import com.roncoo.education.course.dao.impl.mapper.entity.FileStorageExample;

public interface FileStorageDao {
    int save(FileStorage record);

    int deleteById(Long id);

    int updateById(FileStorage record);

    FileStorage getById(Long id);

    Page<FileStorage> listForPage(int pageCurrent, int pageSize, FileStorageExample example);
}
