package com.roncoo.education.course.service.dao.impl;

import com.roncoo.education.course.service.dao.FileStorageDao;
import com.roncoo.education.course.service.dao.impl.mapper.FileStorageMapper;
import com.roncoo.education.course.service.dao.impl.mapper.entity.FileStorage;
import com.roncoo.education.course.service.dao.impl.mapper.entity.FileStorageExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FileStorageDaoImpl implements FileStorageDao {
    @Autowired
    private FileStorageMapper fileStorageMapper;

    public int save(FileStorage record) {
        record.setId(IdWorker.getId());
        return this.fileStorageMapper.insertSelective(record);
    }

    public int deleteById(Long id) {
        return this.fileStorageMapper.deleteByPrimaryKey(id);
    }

    public int updateById(FileStorage record) {
        return this.fileStorageMapper.updateByPrimaryKeySelective(record);
    }

    public FileStorage getById(Long id) {
        return this.fileStorageMapper.selectByPrimaryKey(id);
    }

    public Page<FileStorage> listForPage(int pageCurrent, int pageSize, FileStorageExample example) {
        int count = this.fileStorageMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<FileStorage>(count, totalPage, pageCurrent, pageSize, this.fileStorageMapper.selectByExample(example));
    }
}