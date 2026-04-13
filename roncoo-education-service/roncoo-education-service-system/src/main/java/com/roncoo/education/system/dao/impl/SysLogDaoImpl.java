package com.roncoo.education.system.dao.impl;

import com.roncoo.education.common.base.page.Page;
import com.roncoo.education.common.base.page.PageUtil;
import com.roncoo.education.common.tools.IdWorker;
import com.roncoo.education.system.dao.SysLogDao;
import com.roncoo.education.system.dao.impl.mapper.SysLogMapper;
import com.roncoo.education.system.dao.impl.mapper.entity.SysLog;
import com.roncoo.education.system.dao.impl.mapper.entity.SysLogExample;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import jakarta.validation.constraints.NotNull;

@Repository
@RequiredArgsConstructor
public class SysLogDaoImpl implements SysLogDao {
    @NotNull
    private final SysLogMapper sysLogMapper;

    @Override
    public int save(SysLog record) {
        if (record.getId() == null) {
            record.setId(IdWorker.getId());
        }
        return this.sysLogMapper.insertSelective(record);
    }

    @Override
    public int deleteById(Long id) {
        return this.sysLogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(SysLog record) {
        return this.sysLogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByExampleSelective(SysLog record, SysLogExample example) {
        return this.sysLogMapper.updateByExampleSelective(record, example);
    }

    @Override
    public SysLog getById(Long id) {
        return this.sysLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<SysLog> page(int pageCurrent, int pageSize, SysLogExample example) {
        int count = this.sysLogMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<SysLog>(count, totalPage, pageCurrent, pageSize, this.sysLogMapper.selectByExample(example));
    }
}
