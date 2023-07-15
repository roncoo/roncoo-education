package com.roncoo.education.system.service.admin.biz;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.ResultEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.core.tools.SHA1Util;
import com.roncoo.education.system.dao.SysRoleDao;
import com.roncoo.education.system.dao.SysRoleUserDao;
import com.roncoo.education.system.dao.SysUserDao;
import com.roncoo.education.system.dao.impl.mapper.entity.SysRole;
import com.roncoo.education.system.dao.impl.mapper.entity.SysRoleUser;
import com.roncoo.education.system.dao.impl.mapper.entity.SysUser;
import com.roncoo.education.system.dao.impl.mapper.entity.SysUserExample;
import com.roncoo.education.system.dao.impl.mapper.entity.SysUserExample.Criteria;
import com.roncoo.education.system.service.admin.req.*;
import com.roncoo.education.system.service.admin.resp.AdminSysUserPageResp;
import com.roncoo.education.system.service.admin.resp.AdminSysUserViewResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 后台用户信息
 *
 * @author wujing
 */
@Slf4j
@Component
public class AdminSysUserBiz {

    @Autowired
    private SysUserDao dao;
    @Autowired
    private SysRoleUserDao sysRoleUserDao;
    @Autowired
    private SysRoleDao sysRoleDao;

    public Result<Page<AdminSysUserPageResp>> listForPage(AdminSysUserPageReq req) {
        SysUserExample example = new SysUserExample();
        Criteria c = example.createCriteria();
        if (StringUtils.hasText(req.getMobile())) {
            c.andMobileEqualTo(req.getMobile());
        }
        example.setOrderByClause(" sort asc, id desc ");
        Page<SysUser> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminSysUserPageResp> respPage = PageUtil.transform(page, AdminSysUserPageResp.class);
        if (CollUtil.isNotEmpty(respPage.getList())) {
            List<Long> userIdList = respPage.getList().stream().map(AdminSysUserPageResp::getId).collect(Collectors.toList());
            List<SysRoleUser> roleUserList = sysRoleUserDao.listByUserIds(userIdList);
            if (CollUtil.isNotEmpty(roleUserList)) {
                List<Long> roles = roleUserList.stream().map(SysRoleUser::getRoleId).collect(Collectors.toList());
                List<SysRole> roleList = sysRoleDao.listByIds(roles);
                extracted(respPage, roleUserList, roleList);
            }
        }
        return Result.success(respPage);
    }

    private void extracted(Page<AdminSysUserPageResp> respPage, List<SysRoleUser> roleUserList, List<SysRole> roleList) {
        if (CollUtil.isNotEmpty(roleList)) {
            Map<Long, String> roleNameMap = roleList.stream().collect(Collectors.toMap(SysRole::getId, SysRole::getRoleName));
            Map<Long, List<Long>> map = roleUserList.stream().collect(Collectors.groupingBy(SysRoleUser::getUserId, Collectors.mapping(SysRoleUser::getRoleId, Collectors.toList())));
            for (AdminSysUserPageResp resp : respPage.getList()) {
                List<Long> roleIdList = map.get(resp.getId());
                if (CollUtil.isNotEmpty(roleIdList)) {
                    List<String> roleNameList = new ArrayList<>();
                    for (Long roleId : roleIdList) {
                        roleNameList.add(roleNameMap.get(roleId));
                    }
                    resp.setRoleNameList(roleNameList);
                }
            }
        }
    }

    public Result<String> save(AdminSysUserSaveReq req) {
        if (!req.getMobilePwd().equals(req.getRePassword())) {
            return Result.error("密码不一致");
        }
        SysUser sysUser = dao.getByMobile(req.getMobile());
        if (ObjectUtil.isNotNull(sysUser)) {
            return Result.error("用户已添加成管理员");
        }
        SysUser record = BeanUtil.copyProperties(req, SysUser.class);
        record.setMobileSalt(IdUtil.simpleUUID());
        record.setMobilePsw(SHA1Util.getSign(record.getMobileSalt() + req.getMobilePwd()));
        int results = dao.save(record);
        if (results > 0) {
            return Result.success("操作成功");
        }
        return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
    }

    @Transactional
    public Result<String> delete(AdminSysUserDeleteReq req) {
        if (req.getId() == null) {
            return Result.error("主键ID不能为空");
        }
        // 1、删除用户所有角色
        sysRoleUserDao.deleteByUserId(req.getId());
        // 2、删除用户
        dao.deleteById(req.getId());
        return Result.success("操作成功");
    }

    public Result<String> update(AdminSysUserUpdateReq req) {
        if (req.getId() == null) {
            return Result.error("主键ID不能为空");
        }
        SysUser sysUser = dao.getById(req.getId());
        if (ObjectUtil.isNull(sysUser)) {
            return Result.error("找不到管理员信息");
        }
        SysUser record = BeanUtil.copyProperties(req, SysUser.class);
        int results = dao.updateById(record);
        if (results > 0) {
            return Result.success("操作成功");
        }
        return Result.error(ResultEnum.SYSTEM_UPDATE_FAIL);
    }

    public Result<AdminSysUserViewResp> view(AdminSysUserViewReq req) {
        if (req.getId() == null) {
            return Result.error("主键ID不能为空");
        }
        SysUser sysUser = dao.getById(req.getId());
        if (ObjectUtil.isNull(sysUser)) {
            return Result.error("管理员不存在");
        }
        return Result.success(BeanUtil.copyProperties(sysUser, AdminSysUserViewResp.class));
    }

    public Result<String> updatePassword(AdminSysUserUpdatePasswordReq req) {
        if (req.getUserId() == null) {
            return Result.error("用户ID不能为空,请重试");
        }
        if (StringUtils.isEmpty(req.getMobilePwd())) {
            return Result.error("新密码不能为空,请重试");
        }
        if (StringUtils.isEmpty(req.getConfirmPassword())) {
            return Result.error("确认密码不能为空,请重试");
        }
        if (!req.getConfirmPassword().equals(req.getMobilePwd())) {
            return Result.error("密码不一致,请重试");
        }
        SysUser record = new SysUser();
        record.setId(req.getUserId());
        record.setMobileSalt(IdUtil.simpleUUID());
        record.setMobilePsw(SHA1Util.getSign(record.getMobileSalt() + req.getMobilePwd()));
        dao.updateById(record);
        return Result.success("操作成功");
    }

}
