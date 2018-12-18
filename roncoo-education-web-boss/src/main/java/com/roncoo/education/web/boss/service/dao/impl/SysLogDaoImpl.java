/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.web.boss.service.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.IdWorker;
import com.roncoo.education.web.boss.service.dao.SysLogDao;
import com.roncoo.education.web.boss.service.dao.impl.mapper.SysLogMapper;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysLog;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysLogExample;

@Repository
public class SysLogDaoImpl implements SysLogDao {
    @Autowired
    private SysLogMapper SysLogMapper;

    @Override
	public int save(SysLog record) {
        record.setId(IdWorker.getId());
        return this.SysLogMapper.insertSelective(record);
    }

    @Override
	public int deleteById(Long id) {
        return this.SysLogMapper.deleteByPrimaryKey(id);
    }

    @Override
	public int updateById(SysLog record) {
        return this.SysLogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
	public SysLog getById(Long id) {
        return this.SysLogMapper.selectByPrimaryKey(id);
    }

    @Override
	public Page<SysLog> listForPage(int pageCurrent, int pageSize, SysLogExample example) {
        int count = this.SysLogMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<SysLog>(count, totalPage, pageCurrent, pageSize, this.SysLogMapper.selectByExample(example));
    }
}
