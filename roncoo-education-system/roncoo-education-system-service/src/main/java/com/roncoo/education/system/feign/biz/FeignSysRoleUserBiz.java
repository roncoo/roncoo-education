package com.roncoo.education.system.feign.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.system.dao.SysRoleUserDao;
import com.roncoo.education.system.dao.impl.mapper.entity.SysRoleUser;
import com.roncoo.education.system.dao.impl.mapper.entity.SysRoleUserExample;
import com.roncoo.education.system.dao.impl.mapper.entity.SysRoleUserExample.Criteria;
import com.roncoo.education.system.feign.interfaces.qo.SysRoleUserQO;
import com.roncoo.education.system.feign.interfaces.vo.SysRoleUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 角色用户关联表
 *
 * @author wujing
 */
@Component
public class FeignSysRoleUserBiz {

    @Autowired
    private SysRoleUserDao dao;

    public Page<SysRoleUserVO> listForPage(SysRoleUserQO qo) {
        SysRoleUserExample example = new SysRoleUserExample();
        Criteria c = example.createCriteria();
        example.setOrderByClause(" id desc ");
        Page<SysRoleUser> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, SysRoleUserVO.class);
    }

    public int save(SysRoleUserQO qo) {
        SysRoleUser record = BeanUtil.copyProperties(qo, SysRoleUser.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public SysRoleUserVO getById(Long id) {
        SysRoleUser record = dao.getById(id);
        return BeanUtil.copyProperties(record, SysRoleUserVO.class);
    }

    public int updateById(SysRoleUserQO qo) {
        SysRoleUser record = BeanUtil.copyProperties(qo, SysRoleUser.class);
        return dao.updateById(record);
    }

}
