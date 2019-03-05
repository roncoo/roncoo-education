package com.roncoo.education.system.service.dao.impl;

import com.roncoo.education.system.service.dao.MsgTemplateDao;
import com.roncoo.education.system.service.dao.impl.mapper.MsgTemplateMapper;
import com.roncoo.education.system.service.dao.impl.mapper.entity.MsgTemplate;
import com.roncoo.education.system.service.dao.impl.mapper.entity.MsgTemplateExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MsgTemplateDaoImpl implements MsgTemplateDao {
    @Autowired
    private MsgTemplateMapper msgTemplateMapper;

    public int save(MsgTemplate record) {
        record.setId(IdWorker.getId());
        return this.msgTemplateMapper.insertSelective(record);
    }

    public int deleteById(Long id) {
        return this.msgTemplateMapper.deleteByPrimaryKey(id);
    }

    public int updateById(MsgTemplate record) {
        return this.msgTemplateMapper.updateByPrimaryKeySelective(record);
    }

    public MsgTemplate getById(Long id) {
        return this.msgTemplateMapper.selectByPrimaryKey(id);
    }

    public Page<MsgTemplate> listForPage(int pageCurrent, int pageSize, MsgTemplateExample example) {
        int count = this.msgTemplateMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<MsgTemplate>(count, totalPage, pageCurrent, pageSize, this.msgTemplateMapper.selectByExample(example));
    }
}