package com.roncoo.education.course.dao.impl;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.IdWorker;
import com.roncoo.education.course.dao.ZoneDao;
import com.roncoo.education.course.dao.impl.mapper.ZoneMapper;
import com.roncoo.education.course.dao.impl.mapper.entity.Zone;
import com.roncoo.education.course.dao.impl.mapper.entity.ZoneExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ZoneDaoImpl implements ZoneDao {
    @Autowired
    private ZoneMapper zoneMapper;

    public int save(Zone record) {
        record.setId(IdWorker.getId());
        return this.zoneMapper.insertSelective(record);
    }

    public int deleteById(Long id) {
        return this.zoneMapper.deleteByPrimaryKey(id);
    }

    public int updateById(Zone record) {
        return this.zoneMapper.updateByPrimaryKeySelective(record);
    }

    public Zone getById(Long id) {
        return this.zoneMapper.selectByPrimaryKey(id);
    }

    public Page<Zone> listForPage(int pageCurrent, int pageSize, ZoneExample example) {
        int count = this.zoneMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<Zone>(count, totalPage, pageCurrent, pageSize, this.zoneMapper.selectByExample(example));
    }

    @Override
    public List<Zone> listAllZone() {
        ZoneExample example = new ZoneExample();
        List<Zone> list = this.zoneMapper.selectByExample(example);
        return list;
    }
}
