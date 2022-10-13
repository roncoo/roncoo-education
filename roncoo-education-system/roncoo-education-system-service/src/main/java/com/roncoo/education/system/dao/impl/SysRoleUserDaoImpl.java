package com.roncoo.education.system.dao.impl;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.IdWorker;
import com.roncoo.education.system.dao.SysRoleUserDao;
import com.roncoo.education.system.dao.impl.mapper.SysRoleUserMapper;
import com.roncoo.education.system.dao.impl.mapper.entity.SysRoleUser;
import com.roncoo.education.system.dao.impl.mapper.entity.SysRoleUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SysRoleUserDaoImpl implements SysRoleUserDao {
    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Override
    public int save(SysRoleUser record) {
        if (record.getId() == null) {
            record.setId(IdWorker.getId());
        }
        return this.sysRoleUserMapper.insertSelective(record);
    }

    @Override
    public int deleteById(Long id) {
        return this.sysRoleUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(SysRoleUser record) {
        return this.sysRoleUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByExampleSelective(SysRoleUser record, SysRoleUserExample example) {
        return this.sysRoleUserMapper.updateByExampleSelective(record, example);
    }

    @Override
    public SysRoleUser getById(Long id) {
        return this.sysRoleUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<SysRoleUser> page(int pageCurrent, int pageSize, SysRoleUserExample example) {
        int count = this.sysRoleUserMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<SysRoleUser>(count, totalPage, pageCurrent, pageSize, this.sysRoleUserMapper.selectByExample(example));
    }

    @Override
    public int deleteByUserId(Long userId) {
        SysRoleUserExample example = new SysRoleUserExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return this.sysRoleUserMapper.deleteByExample(example);
    }

    @Override
    public List<SysRoleUser> listByUserId(Long userId) {
        SysRoleUserExample example = new SysRoleUserExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return this.sysRoleUserMapper.selectByExample(example);
    }

    @Override
    public List<SysRoleUser> listByUserIds(List<Long> userIdList) {
        SysRoleUserExample example = new SysRoleUserExample();
        example.createCriteria().andUserIdIn(userIdList);
        return this.sysRoleUserMapper.selectByExample(example);
    }
}
