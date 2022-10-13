package com.roncoo.education.system.service.admin.biz;

import cn.hutool.core.collection.CollectionUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.dao.SysRoleDao;
import com.roncoo.education.system.dao.SysRoleUserDao;
import com.roncoo.education.system.dao.impl.mapper.entity.SysRoleUser;
import com.roncoo.education.system.service.admin.req.AdminSysRoleUserListReq;
import com.roncoo.education.system.service.admin.req.AdminSysRoleUserSaveReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public Result<List<Long>> list(AdminSysRoleUserListReq req) {
        if (req.getUserId() == null) {
            return Result.error("用户ID不能为空");
        }
        List<SysRoleUser> list = dao.listByUserId(req.getUserId());
        if (CollectionUtil.isNotEmpty(list)) {
            List<Long> roleIdList = list.stream().map(SysRoleUser::getRoleId).collect(Collectors.toList());
            return Result.success(roleIdList);
        }
        return Result.success(new ArrayList<>());
    }

    @Transactional(rollbackFor = Exception.class)
    public Result<String> save(AdminSysRoleUserSaveReq req) {
        if (req.getUserId() == null) {
            return Result.error("用户ID不能为空");
        }
        // 先删除旧的角色
        dao.deleteByUserId(req.getUserId());
        // 再新增
        if (CollectionUtil.isNotEmpty(req.getRoleIdList())) {
            for (Long roleId : req.getRoleIdList()) {
                SysRoleUser sysRoleUser = new SysRoleUser();
                sysRoleUser.setRoleId(roleId);
                sysRoleUser.setUserId(req.getUserId());
                dao.save(sysRoleUser);
            }
        }
        return Result.success("操作成功");
    }
}
