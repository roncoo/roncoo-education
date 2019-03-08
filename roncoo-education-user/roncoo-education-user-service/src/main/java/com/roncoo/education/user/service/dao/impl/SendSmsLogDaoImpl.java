package com.roncoo.education.user.service.dao.impl;

import com.roncoo.education.user.service.dao.SendSmsLogDao;
import com.roncoo.education.user.service.dao.impl.mapper.SendSmsLogMapper;
import com.roncoo.education.user.service.dao.impl.mapper.entity.SendSmsLog;
import com.roncoo.education.user.service.dao.impl.mapper.entity.SendSmsLogExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SendSmsLogDaoImpl implements SendSmsLogDao {
    @Autowired
    private SendSmsLogMapper sendSmsLogMapper;

    public int save(SendSmsLog record) {
        record.setId(IdWorker.getId());
        return this.sendSmsLogMapper.insertSelective(record);
    }

    public int deleteById(Long id) {
        return this.sendSmsLogMapper.deleteByPrimaryKey(id);
    }

    public int updateById(SendSmsLog record) {
        return this.sendSmsLogMapper.updateByPrimaryKeySelective(record);
    }

    public SendSmsLog getById(Long id) {
        return this.sendSmsLogMapper.selectByPrimaryKey(id);
    }

    public Page<SendSmsLog> listForPage(int pageCurrent, int pageSize, SendSmsLogExample example) {
        int count = this.sendSmsLogMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<SendSmsLog>(count, totalPage, pageCurrent, pageSize, this.sendSmsLogMapper.selectByExample(example));
    }
}