package com.roncoo.education.system.service.pc.biz;

import cn.hutool.core.collection.CollectionUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.system.dao.SysRoleDao;
import com.roncoo.education.system.dao.SysRoleUserDao;
import com.roncoo.education.system.dao.impl.mapper.entity.SysRole;
import com.roncoo.education.system.dao.impl.mapper.entity.SysRoleUser;
import com.roncoo.education.system.service.pc.req.SysRoleUserListREQ;
import com.roncoo.education.system.service.pc.req.SysRoleUserSaveREQ;
import com.roncoo.education.system.service.pc.resq.SysRoleUserListRESQ;
import com.roncoo.education.system.service.pc.resq.SysRoleUserRESQ;
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
public class PcApiSysRoleUserBiz {

    @Autowired
    private SysRoleUserDao dao;
    @Autowired
    private SysRoleDao sysRoleDao;

    public Result<SysRoleUserListRESQ> list(SysRoleUserListREQ req) {
        if (req.getUserId() == null) {
            return Result.error("用户ID不能为空");
        }
        SysRoleUserListRESQ resq = new SysRoleUserListRESQ();
        List<SysRoleUser> list = dao.listByUserId(req.getUserId());
        if (CollectionUtil.isNotEmpty(list)) {
            List<SysRoleUserRESQ> roleList = new ArrayList<>();
            for (SysRoleUser sysRoleUser : list) {
                SysRole sysRole = sysRoleDao.getById(sysRoleUser.getRoleId());
                roleList.add(BeanUtil.copyProperties(sysRole, SysRoleUserRESQ.class));
            }
            resq.setList(roleList);
        }
        return Result.success(resq);
    }

    @Transactional
    public Result<Integer> save(SysRoleUserSaveREQ req) {
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
