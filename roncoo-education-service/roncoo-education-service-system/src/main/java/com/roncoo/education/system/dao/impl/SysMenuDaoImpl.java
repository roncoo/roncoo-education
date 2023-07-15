package com.roncoo.education.system.dao.impl;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.IdWorker;
import com.roncoo.education.system.dao.SysMenuDao;
import com.roncoo.education.system.dao.impl.mapper.SysMenuMapper;
import com.roncoo.education.system.dao.impl.mapper.entity.SysMenu;
import com.roncoo.education.system.dao.impl.mapper.entity.SysMenuExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SysMenuDaoImpl implements SysMenuDao {
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public int save(SysMenu record) {
        if (record.getId() == null) {
            record.setId(IdWorker.getId());
        }
        return this.sysMenuMapper.insertSelective(record);
    }

    @Override
    public int deleteById(Long id) {
        return this.sysMenuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(SysMenu record) {
        return this.sysMenuMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByExampleSelective(SysMenu record, SysMenuExample example) {
        return this.sysMenuMapper.updateByExampleSelective(record, example);
    }

    @Override
    public SysMenu getById(Long id) {
        return this.sysMenuMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<SysMenu> page(int pageCurrent, int pageSize, SysMenuExample example) {
        int count = this.sysMenuMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<SysMenu>(count, totalPage, pageCurrent, pageSize, this.sysMenuMapper.selectByExampleWithBLOBs(example));
    }

    @Override
    public List<SysMenu> listByParentId(Long parentId) {
        SysMenuExample example = new SysMenuExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        example.setOrderByClause(" sort asc, id desc");
        return this.sysMenuMapper.selectByExample(example);
    }

    @Override
    public List<SysMenu> getByIds(List<Long> ids) {
        SysMenuExample example = new SysMenuExample();
        example.createCriteria().andIdIn(ids);
        return this.sysMenuMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public List<SysMenu> listByExample(SysMenuExample example) {
        return this.sysMenuMapper.selectByExample(example);
    }

    @Override
    public List<SysMenu> selectByExampleWithBLOBs(SysMenuExample example) {
        return this.sysMenuMapper.selectByExampleWithBLOBs(example);
    }
}
