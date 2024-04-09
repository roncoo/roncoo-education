package com.roncoo.education.system.service.admin.biz;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.ResultEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.system.dao.SysMenuDao;
import com.roncoo.education.system.dao.impl.mapper.entity.SysMenu;
import com.roncoo.education.system.dao.impl.mapper.entity.SysMenuExample;
import com.roncoo.education.system.service.admin.req.*;
import com.roncoo.education.system.service.admin.resp.AdminSysMenuResp;
import com.roncoo.education.system.service.admin.resp.AdminSysMenuViewResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单信息
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AdminSysMenuBiz {

    @NotNull
    private final SysMenuDao dao;

    public Result<List<AdminSysMenuResp>> list(AdminSysMenuListReq req) {
        SysMenuExample example = new SysMenuExample();
        SysMenuExample.Criteria c = example.createCriteria();
        if (StringUtils.hasText(req.getMenuName())) {
            c.andMenuNameEqualTo(req.getMenuName());
        }
        if (ObjectUtil.isNotEmpty(req.getStatusId())) {
            c.andStatusIdEqualTo(req.getStatusId());
        }
        example.setOrderByClause("sort asc, id desc");
        List<SysMenu> sysMenuList = dao.listByExample(example);
        return Result.success(filter(0L, sysMenuList));
    }

    /**
     * 菜单层级处理
     */
    private List<AdminSysMenuResp> filter(Long parentId, List<SysMenu> menuList) {
        List<SysMenu> sysMenuList = menuList.stream().filter(item -> parentId.compareTo(item.getParentId()) == 0).collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(sysMenuList)) {
            List<AdminSysMenuResp> respList = BeanUtil.copyProperties(sysMenuList, AdminSysMenuResp.class);
            for (AdminSysMenuResp resp : respList) {
                resp.setChildrenList(filter(resp.getId(), menuList));
            }
            return respList;
        }
        return null;
    }

    public Result<String> save(AdminSysMenuSaveReq req) {
        SysMenu record = BeanUtil.copyProperties(req, SysMenu.class);
        int results = dao.save(record);
        if (results > 0) {
            return Result.success("操作成功");
        }
        return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
    }

    public Result<String> delete(AdminSysMenuDeleteReq req) {
        if (req.getId() == null) {
            return Result.error("主键ID不能为空");
        }
        // 存在子菜单则不允许删除
        List<SysMenu> list = dao.listByParentId(req.getId());
        if (CollectionUtil.isNotEmpty(list)) {
            return Result.error("请先删除下级菜单");
        }
        int results = dao.deleteById(req.getId());
        if (results > 0) {
            return Result.success("操作成功");
        }
        return Result.error(ResultEnum.SYSTEM_DELETE_FAIL);
    }

    public Result<String> update(AdminSysMenuUpdateReq req) {
        if (req.getId() == null) {
            return Result.error("主键ID不能为空");
        }
        SysMenu sysMenu = dao.getById(req.getId());
        if (ObjectUtil.isNull(sysMenu)) {
            return Result.error("找不到菜单信息");
        }
        SysMenu record = BeanUtil.copyProperties(req, SysMenu.class);
        int results = dao.updateById(record);
        if (results > 0) {
            return Result.success("操作成功");
        }
        return Result.error(ResultEnum.SYSTEM_UPDATE_FAIL);
    }

    public Result<AdminSysMenuViewResp> view(AdminSysMenuViewReq req) {
        if (req.getId() == null) {
            return Result.error("主键ID不能为空");
        }
        SysMenu record = dao.getById(req.getId());
        if (ObjectUtil.isNull(record)) {
            return Result.error("找不到菜单信息");
        }
        return Result.success(BeanUtil.copyProperties(record, AdminSysMenuViewResp.class));
    }


}
