package com.roncoo.education.system.dao.impl;

import com.roncoo.education.common.base.page.Page;
import com.roncoo.education.common.base.page.PageUtil;
import com.roncoo.education.common.tools.IdWorker;
import com.roncoo.education.system.dao.SysUserDao;
import com.roncoo.education.system.dao.impl.mapper.SysUserMapper;
import com.roncoo.education.system.dao.impl.mapper.entity.SysUser;
import com.roncoo.education.system.dao.impl.mapper.entity.SysUserExample;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import jakarta.validation.constraints.NotNull;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SysUserDaoImpl implements SysUserDao {
    @NotNull
    private final SysUserMapper sysUserMapper;

    @Override
    public int save(SysUser record) {
        if (record.getId() == null) {
            record.setId(IdWorker.getId());
        }
        return this.sysUserMapper.insertSelective(record);
    }

    @Override
    public int deleteById(Long id) {
        return this.sysUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(SysUser record) {
        return this.sysUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByExampleSelective(SysUser record, SysUserExample example) {
        return this.sysUserMapper.updateByExampleSelective(record, example);
    }

    @Override
    public SysUser getById(Long id) {
        return this.sysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<SysUser> page(int pageCurrent, int pageSize, SysUserExample example) {
        int count = this.sysUserMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<SysUser>(count, totalPage, pageCurrent, pageSize, this.sysUserMapper.selectByExample(example));
    }

    @Override
    public SysUser getByMobile(String mobile) {
        SysUserExample example = new SysUserExample();
        example.createCriteria().andMobileEqualTo(mobile);
        List<SysUser> list = this.sysUserMapper.selectByExample(example);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<SysUser> listByIds(List<Long> userIds) {
        SysUserExample example = new SysUserExample();
        example.createCriteria().andIdIn(userIds);
        return this.sysUserMapper.selectByExample(example);
    }

}
