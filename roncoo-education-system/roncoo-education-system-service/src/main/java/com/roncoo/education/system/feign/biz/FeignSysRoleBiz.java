package com.roncoo.education.system.feign.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.system.dao.SysRoleDao;
import com.roncoo.education.system.dao.impl.mapper.entity.SysRole;
import com.roncoo.education.system.dao.impl.mapper.entity.SysRoleExample;
import com.roncoo.education.system.dao.impl.mapper.entity.SysRoleExample.Criteria;
import com.roncoo.education.system.feign.interfaces.qo.SysRoleQO;
import com.roncoo.education.system.feign.interfaces.vo.SysRoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 角色信息
 *
 * @author wujing
 */
@Component
public class FeignSysRoleBiz {

    @Autowired
    private SysRoleDao dao;

    public Page<SysRoleVO> listForPage(SysRoleQO qo) {
        SysRoleExample example = new SysRoleExample();
        Criteria c = example.createCriteria();
        example.setOrderByClause(" id desc ");
        Page<SysRole> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, SysRoleVO.class);
    }

    public int save(SysRoleQO qo) {
        SysRole record = BeanUtil.copyProperties(qo, SysRole.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public SysRoleVO getById(Long id) {
        SysRole record = dao.getById(id);
        return BeanUtil.copyProperties(record, SysRoleVO.class);
    }

    public int updateById(SysRoleQO qo) {
        SysRole record = BeanUtil.copyProperties(qo, SysRole.class);
        return dao.updateById(record);
    }

}
