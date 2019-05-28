package com.roncoo.education.system.service.dao.impl;

import com.roncoo.education.system.service.dao.SysLogDao;
import com.roncoo.education.system.service.dao.impl.mapper.SysLogMapper;
import com.roncoo.education.system.service.dao.impl.mapper.entity.SysLog;
import com.roncoo.education.system.service.dao.impl.mapper.entity.SysLogExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SysLogDaoImpl implements SysLogDao {
    @Autowired
    private SysLogMapper sysLogMapper;

    public int save(SysLog record) {
        record.setId(IdWorker.getId());
        return this.sysLogMapper.insertSelective(record);
    }

    public int deleteById(Long id) {
        return this.sysLogMapper.deleteByPrimaryKey(id);
    }

    public int updateById(SysLog record) {
        return this.sysLogMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByExampleSelective(SysLog record, SysLogExample example) {
        return this.sysLogMapper.updateByExampleSelective(record, example);
    }

    public SysLog getById(Long id) {
        return this.sysLogMapper.selectByPrimaryKey(id);
    }

    public Page<SysLog> listForPage(int pageCurrent, int pageSize, SysLogExample example) {
        int count = this.sysLogMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<SysLog>(count, totalPage, pageCurrent, pageSize, this.sysLogMapper.selectByExample(example));
    }
}