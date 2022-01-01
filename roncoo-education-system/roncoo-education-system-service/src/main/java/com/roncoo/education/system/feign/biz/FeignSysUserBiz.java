package com.roncoo.education.system.feign.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.system.dao.SysUserDao;
import com.roncoo.education.system.dao.impl.mapper.entity.SysUser;
import com.roncoo.education.system.dao.impl.mapper.entity.SysUserExample;
import com.roncoo.education.system.dao.impl.mapper.entity.SysUserExample.Criteria;
import com.roncoo.education.system.feign.interfaces.qo.SysUserQO;
import com.roncoo.education.system.feign.interfaces.vo.SysUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 后台用户信息
 *
 * @author wujing
 */
@Component
public class FeignSysUserBiz {

    @Autowired
    private SysUserDao dao;

    public Page<SysUserVO> listForPage(SysUserQO qo) {
        SysUserExample example = new SysUserExample();
        Criteria c = example.createCriteria();
        example.setOrderByClause(" id desc ");
        Page<SysUser> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, SysUserVO.class);
    }

    public int save(SysUserQO qo) {
        SysUser record = BeanUtil.copyProperties(qo, SysUser.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public SysUserVO getById(Long id) {
        SysUser record = dao.getById(id);
        return BeanUtil.copyProperties(record, SysUserVO.class);
    }

    public int updateById(SysUserQO qo) {
        SysUser record = BeanUtil.copyProperties(qo, SysUser.class);
        return dao.updateById(record);
    }

}
