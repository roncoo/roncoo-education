package com.roncoo.education.course.service.dao;

import com.roncoo.education.course.service.dao.impl.mapper.entity.FileStorage;
import com.roncoo.education.course.service.dao.impl.mapper.entity.FileStorageExample;
import com.roncoo.education.util.base.Page;

public interface FileStorageDao {
    int save(FileStorage record);

    int deleteById(Long id);

    int updateById(FileStorage record);

    FileStorage getById(Long id);

    Page<FileStorage> listForPage(int pageCurrent, int pageSize, FileStorageExample example);
}