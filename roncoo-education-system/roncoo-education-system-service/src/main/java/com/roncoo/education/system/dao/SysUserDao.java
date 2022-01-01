package com.roncoo.education.system.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.system.dao.impl.mapper.entity.SysUser;
import com.roncoo.education.system.dao.impl.mapper.entity.SysUserExample;

public interface SysUserDao {
    int save(SysUser record);

    int deleteById(Long id);

    int updateById(SysUser record);

    int updateByExampleSelective(SysUser record, SysUserExample example);

    SysUser getById(Long id);

    Page<SysUser> listForPage(int pageCurrent, int pageSize, SysUserExample example);

    /**
     * 根据用户编号获取管理员信息
     *
     * @param userNo
     * @return
     */
    SysUser getByUserNo(Long userNo);
}
