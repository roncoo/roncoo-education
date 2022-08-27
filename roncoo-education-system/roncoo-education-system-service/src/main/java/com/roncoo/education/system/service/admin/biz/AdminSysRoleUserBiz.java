package com.roncoo.education.system.service.admin.biz;

import cn.hutool.core.collection.CollectionUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.system.dao.SysRoleDao;
import com.roncoo.education.system.dao.SysRoleUserDao;
import com.roncoo.education.system.dao.impl.mapper.entity.SysRole;
import com.roncoo.education.system.dao.impl.mapper.entity.SysRoleUser;
import com.roncoo.education.system.service.admin.req.AdminSysRoleUserListReq;
import com.roncoo.education.system.service.admin.req.AdminSysRoleUserSaveReq;
import com.roncoo.education.system.service.admin.resp.AdminSysRoleUserResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色用户关联表
 *
 * @author wujing
 */
@Component
public class AdminSysRoleUserBiz {

    @Autowired
    private SysRoleUserDao dao;
    @Autowired
    private SysRoleDao sysRoleDao;

    public Result<List<AdminSysRoleUserResp>> list(AdminSysRoleUserListReq req) {
        if (req.getUserId() == null) {
            return Result.error("用户ID不能为空");
        }
        List<SysRoleUser> list = dao.listByUserId(req.getUserId());
        if (CollectionUtil.isNotEmpty(list)) {
            List<AdminSysRoleUserResp> roleList = new ArrayList<>();
            for (SysRoleUser sysRoleUser : list) {
                SysRole sysRole = sysRoleDao.getById(sysRoleUser.getRoleId());
                roleList.add(BeanUtil.copyProperties(sysRole, AdminSysRoleUserResp.class));
            }
            return Result.success(roleList);
        }
        return Result.success(new ArrayList<>());
    }

    @Transactional
    public Result<Integer> save(AdminSysRoleUserSaveReq req) {
        if (req.getUserId() == null) {
            return Result.error("用户ID不能为空");
        }
        // 先删除旧的，再添加新的
        dao.deleteByUserId(req.getUserId());
        if (CollectionUtil.isNotEmpty(req.getRoleId())) {
            // 拆分角色和平台拼接ID
            for (Long roleId : req.getRoleId()) {
                SysRoleUser sysRoleUser = new SysRoleUser();
                sysRoleUser.setRoleId(roleId);
                sysRoleUser.setUserId(req.getUserId());
                dao.save(sysRoleUser);
            }
        }
        return Result.success(1);
    }
}
