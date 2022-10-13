package com.roncoo.education.system.service.admin.biz;

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
import com.roncoo.education.system.service.admin.req.*;
import com.roncoo.education.system.service.admin.resp.AdminSysRolePageResp;
import com.roncoo.education.system.service.admin.resp.AdminSysRoleViewResp;
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
public class AdminSysRoleBiz {

    @Autowired
    private SysRoleDao dao;

    @Autowired
    private SysMenuRoleDao sysMenuRoleDao;

    public Result<Page<AdminSysRolePageResp>> listForPage(AdminSysRolePageReq req) {
        SysRoleExample example = new SysRoleExample();
        Criteria c = example.createCriteria();
        if (StringUtils.hasText(req.getRoleName())) {
            c.andRoleNameEqualTo(req.getRoleName());
        }
        if (req.getStatusId() != null) {
            c.andStatusIdEqualTo(req.getStatusId());
        }
        example.setOrderByClause(" sort asc, id desc ");
        Page<SysRole> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        return Result.success(PageUtil.transform(page, AdminSysRolePageResp.class));
    }

    public Result<String> save(AdminSysRoleSaveReq req) {
        if (StringUtils.isEmpty(req.getRoleName())) {
            return Result.error("角色名称不能为空");
        }
        SysRole record = BeanUtil.copyProperties(req, SysRole.class);
        int results = dao.save(record);
        if (results > 0) {
            return Result.success("操作成功");
        }
        return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
    }

    @Transactional(rollbackFor = Exception.class)
    public Result<String> delete(AdminSysRoleDeleteReq req) {
        if (StringUtils.isEmpty(req.getId())) {
            return Result.error("角色ID不能为空");
        }
        // 1、删除角色下的菜单
        sysMenuRoleDao.deleteByRoleId(req.getId());
        // 2、删除角色
        int results = dao.deleteById(req.getId());
        if (results > 0) {
            return Result.success("操作成功");
        }
        return Result.error(ResultEnum.SYSTEM_DELETE_FAIL);
    }

    public Result<String> update(AdminSysRoleUpdateReq req) {
        if (req.getId() == null) {
            return Result.error("角色ID不能为空");
        }
        SysRole record = BeanUtil.copyProperties(req, SysRole.class);
        int results = dao.updateById(record);
        if (results > 0) {
            return Result.success("操作成功");
        }
        return Result.error(ResultEnum.SYSTEM_UPDATE_FAIL);
    }

    public Result<AdminSysRoleViewResp> view(AdminSysRoleViewReq req) {
        if (req.getId() == null) {
            return Result.error("角色ID不能为空");
        }
        SysRole record = dao.getById(req.getId());
        if (ObjectUtil.isNull(record)) {
            return Result.error("找不到角色信息");
        }
        return Result.success(BeanUtil.copyProperties(record, AdminSysRoleViewResp.class));
    }

}
