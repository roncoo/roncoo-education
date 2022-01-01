package com.roncoo.education.system.dao.impl;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.IdWorker;
import com.roncoo.education.system.dao.SysMenuRoleDao;
import com.roncoo.education.system.dao.impl.mapper.SysMenuRoleMapper;
import com.roncoo.education.system.dao.impl.mapper.entity.SysMenuRole;
import com.roncoo.education.system.dao.impl.mapper.entity.SysMenuRoleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SysMenuRoleDaoImpl implements SysMenuRoleDao {
    @Autowired
    private SysMenuRoleMapper sysMenuRoleMapper;

    public int save(SysMenuRole record) {
        record.setId(IdWorker.getId());
        return this.sysMenuRoleMapper.insertSelective(record);
    }

    public int deleteById(Long id) {
        return this.sysMenuRoleMapper.deleteByPrimaryKey(id);
    }

    public int updateById(SysMenuRole record) {
        return this.sysMenuRoleMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByExampleSelective(SysMenuRole record, SysMenuRoleExample example) {
        return this.sysMenuRoleMapper.updateByExampleSelective(record, example);
    }

    public SysMenuRole getById(Long id) {
        return this.sysMenuRoleMapper.selectByPrimaryKey(id);
    }

    public Page<SysMenuRole> listForPage(int pageCurrent, int pageSize, SysMenuRoleExample example) {
        int count = this.sysMenuRoleMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<SysMenuRole>(count, totalPage, pageCurrent, pageSize, this.sysMenuRoleMapper.selectByExample(example));
    }

    @Override
    public List<SysMenuRole> listByRoleId(Long roleId) {
        SysMenuRoleExample example = new SysMenuRoleExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        return this.sysMenuRoleMapper.selectByExample(example);
    }

    @Override
    public int deleteByRoleId(Long roleId) {
        SysMenuRoleExample example = new SysMenuRoleExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        return this.sysMenuRoleMapper.deleteByExample(example);
    }
}
