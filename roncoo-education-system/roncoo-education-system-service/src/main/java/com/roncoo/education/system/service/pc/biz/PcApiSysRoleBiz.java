package com.roncoo.education.system.service.pc.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.ResultEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.system.dao.SysMenuRoleDao;
import com.roncoo.education.system.dao.SysRoleDao;
import com.roncoo.education.system.dao.impl.mapper.entity.SysRole;
import com.roncoo.education.system.dao.impl.mapper.entity.SysRoleExample;
import com.roncoo.education.system.dao.impl.mapper.entity.SysRoleExample.Criteria;
import com.roncoo.education.system.service.pc.req.*;
import com.roncoo.education.system.service.pc.resq.SysRolePageRESQ;
import com.roncoo.education.system.service.pc.resq.SysRoleViewRESQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * 角色信息
 *
 * @author wujing
 */
@Component
public class PcApiSysRoleBiz {

    @Autowired
    private SysRoleDao dao;

    @Autowired
    private SysMenuRoleDao sysMenuRoleDao;

    public Result<Page<SysRolePageRESQ>> list(SysRolePageREQ req) {
        SysRoleExample example = new SysRoleExample();
        Criteria c = example.createCriteria();
        if (StringUtils.hasText(req.getRoleName())) {
            c.andRoleNameEqualTo(req.getRoleName());
        }
        if (req.getStatusId() != null) {
            c.andStatusIdEqualTo(req.getStatusId());
        }
        example.setOrderByClause(" status_id desc, sort desc, id desc ");
        Page<SysRole> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
        return Result.success(PageUtil.transform(page, SysRolePageRESQ.class));
    }

    public Result<Integer> save(SysRoleSaveREQ req) {
        if (StringUtils.isEmpty(req.getRoleName())) {
            return Result.error("角色名称不能为空");
        }
        SysRole record = BeanUtil.copyProperties(req, SysRole.class);
        int results = dao.save(record);
        if (results > 0) {
            return Result.success(results);
        }
        return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
    }

    @Transactional
    public Result<Integer> delete(SysRoleDeleteREQ req) {
        if (StringUtils.isEmpty(req.getId())) {
            return Result.error("角色ID不能为空");
        }
        // 1、删除角色下的菜单
        sysMenuRoleDao.deleteByRoleId(req.getId());
        // 2、删除角色
        int results = dao.deleteById(req.getId());
        if (results > 0) {
            return Result.success(results);
        }
        return Result.error(ResultEnum.SYSTEM_DELETE_FAIL);
    }

    public Result<Integer> update(SysRoleUpdateREQ req) {
        if (req.getId() == null) {
            return Result.error("角色ID不能为空");
        }
        SysRole record = BeanUtil.copyProperties(req, SysRole.class);
        int results = dao.updateById(record);
        if (results > 0) {
            return Result.success(results);
        }
        return Result.error(ResultEnum.SYSTEM_UPDATE_FAIL);
    }

    public Result<SysRoleViewRESQ> view(SysRoleViewREQ req) {
        if (req.getId() == null) {
            return Result.error("角色ID不能为空");
        }
        SysRole record = dao.getById(req.getId());
        if (ObjectUtil.isNull(record)) {
            return Result.error("找不到角色信息");
        }
        return Result.success(BeanUtil.copyProperties(record, SysRoleViewRESQ.class));
    }

}
