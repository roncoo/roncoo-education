package com.roncoo.education.system.feign.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.system.dao.SysMenuRoleDao;
import com.roncoo.education.system.dao.impl.mapper.entity.SysMenuRole;
import com.roncoo.education.system.dao.impl.mapper.entity.SysMenuRoleExample;
import com.roncoo.education.system.dao.impl.mapper.entity.SysMenuRoleExample.Criteria;
import com.roncoo.education.system.feign.interfaces.qo.SysMenuRoleQO;
import com.roncoo.education.system.feign.interfaces.vo.SysMenuRoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 菜单角色关联表
 *
 * @author wujing
 */
@Component
public class FeignSysMenuRoleBiz {

    @Autowired
    private SysMenuRoleDao dao;

    public Page<SysMenuRoleVO> listForPage(SysMenuRoleQO qo) {
        SysMenuRoleExample example = new SysMenuRoleExample();
        Criteria c = example.createCriteria();
        example.setOrderByClause(" id desc ");
        Page<SysMenuRole> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, SysMenuRoleVO.class);
    }

    public int save(SysMenuRoleQO qo) {
        SysMenuRole record = BeanUtil.copyProperties(qo, SysMenuRole.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public SysMenuRoleVO getById(Long id) {
        SysMenuRole record = dao.getById(id);
        return BeanUtil.copyProperties(record, SysMenuRoleVO.class);
    }

    public int updateById(SysMenuRoleQO qo) {
        SysMenuRole record = BeanUtil.copyProperties(qo, SysMenuRole.class);
        return dao.updateById(record);
    }

}
