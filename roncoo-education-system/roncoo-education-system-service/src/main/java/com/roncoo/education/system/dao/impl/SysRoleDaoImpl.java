package com.roncoo.education.system.dao.impl;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.IdWorker;
import com.roncoo.education.system.dao.SysRoleDao;
import com.roncoo.education.system.dao.impl.mapper.SysRoleMapper;
import com.roncoo.education.system.dao.impl.mapper.entity.SysRole;
import com.roncoo.education.system.dao.impl.mapper.entity.SysRoleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SysRoleDaoImpl implements SysRoleDao {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    public int save(SysRole record) {
        record.setId(IdWorker.getId());
        return this.sysRoleMapper.insertSelective(record);
    }

    public int deleteById(Long id) {
        return this.sysRoleMapper.deleteByPrimaryKey(id);
    }

    public int updateById(SysRole record) {
        return this.sysRoleMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByExampleSelective(SysRole record, SysRoleExample example) {
        return this.sysRoleMapper.updateByExampleSelective(record, example);
    }

    public SysRole getById(Long id) {
        return this.sysRoleMapper.selectByPrimaryKey(id);
    }

    public Page<SysRole> listForPage(int pageCurrent, int pageSize, SysRoleExample example) {
        int count = this.sysRoleMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<SysRole>(count, totalPage, pageCurrent, pageSize, this.sysRoleMapper.selectByExample(example));
    }
}
