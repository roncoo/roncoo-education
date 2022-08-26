package com.roncoo.education.system.service.admin.biz;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.ResultEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.core.tools.SHA1Util;
import com.roncoo.education.system.dao.SysRoleUserDao;
import com.roncoo.education.system.dao.SysUserDao;
import com.roncoo.education.system.dao.impl.mapper.entity.SysUser;
import com.roncoo.education.system.dao.impl.mapper.entity.SysUserExample;
import com.roncoo.education.system.dao.impl.mapper.entity.SysUserExample.Criteria;
import com.roncoo.education.system.service.admin.req.*;
import com.roncoo.education.system.service.admin.resp.SysUserPageRESQ;
import com.roncoo.education.system.service.admin.resp.SysUserViewRESQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * 后台用户信息
 *
 * @author wujing
 */
@Component
public class AdminSysUserBiz {

    @Autowired
    private SysRoleUserDao sysRoleUserDao;
    @Autowired
    private SysUserDao dao;

    public Result<Page<SysUserPageRESQ>> list(SysUserPageREQ req) {
        SysUserExample example = new SysUserExample();
        Criteria c = example.createCriteria();
        if (StringUtils.hasText(req.getMobile())) {
            c.andMobileEqualTo(req.getMobile());
        }
        example.setOrderByClause(" status_id desc, sort asc, id desc ");
        Page<SysUser> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        return Result.success(PageUtil.transform(page, SysUserPageRESQ.class));
    }

    public Result<Integer> save(SysUserSaveREQ req) {
        if (!req.getPassword().equals(req.getRepassword())) {
            return Result.error("密码不一致");
        }
        SysUser sysUser = dao.getByMobile(req.getMobile());
        if (ObjectUtil.isNotNull(sysUser)) {
            return Result.error("用户已添加成管理员");
        }
        SysUser record = BeanUtil.copyProperties(req, SysUser.class);
        record.setMobileSalt(IdUtil.fastUUID());
        record.setMobilePsw(SHA1Util.getSign(record.getMobileSalt() + req.getPassword()));
        int results = dao.save(record);
        if (results > 0) {
            return Result.success(results);
        }
        return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
    }

    @Transactional
    public Result<Integer> delete(SysUserDeleteREQ req) {
        if (req.getId() == null) {
            return Result.error("主键ID不能为空");
        }
        // 1、删除用户所有角色
        sysRoleUserDao.deleteByUserId(req.getId());
        // 2、删除用户
        return Result.success(dao.deleteById(req.getId()));
    }

    public Result<Integer> update(SysUserUpdateREQ req) {
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
            return Result.success(results);
        }
        return Result.error(ResultEnum.SYSTEM_UPDATE_FAIL);
    }

    public Result<SysUserViewRESQ> view(SysUserViewREQ req) {
        if (req.getId() == null) {
            return Result.error("主键ID不能为空");
        }
        SysUser sysUser = dao.getById(req.getId());
        if (ObjectUtil.isNull(sysUser)) {
            return Result.error("管理员不存在");
        }
        return Result.success(BeanUtil.copyProperties(sysUser, SysUserViewRESQ.class));
    }

    public Result<Integer> updatePassword(SysUserUpdatePasswordREQ req) {
        if (req.getUserId() == null) {
            return Result.error("用户ID不能为空,请重试");
        }
        if (StringUtils.isEmpty(req.getMobilePsw())) {
            return Result.error("新密码不能为空,请重试");
        }
        if (StringUtils.isEmpty(req.getRePwd())) {
            return Result.error("确认密码不能为空,请重试");
        }
        if (!req.getRePwd().equals(req.getMobilePsw())) {
            return Result.error("密码不一致,请重试");
        }

        return Result.error(ResultEnum.SYSTEM_UPDATE_FAIL);
    }

}
