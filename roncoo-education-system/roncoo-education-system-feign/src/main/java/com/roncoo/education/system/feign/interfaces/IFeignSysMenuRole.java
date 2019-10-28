package com.roncoo.education.system.feign.interfaces;

import com.roncoo.education.system.feign.qo.SysMenuRoleQO;
import com.roncoo.education.system.feign.vo.SysMenuRoleVO;
import com.roncoo.education.util.base.Page;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 菜单角色关联表
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-system-service")
public interface IFeignSysMenuRole {

    @RequestMapping(value = "/feign/system/sysMenuRole/listForPage")
    Page<SysMenuRoleVO> listForPage(@RequestBody SysMenuRoleQO qo);

    @RequestMapping(value = "/feign/system/sysMenuRole/save")
    int save(@RequestBody SysMenuRoleQO qo);

    @RequestMapping(value = "/feign/system/sysMenuRole/deleteById")
    int deleteById(@RequestBody Long id);

    @RequestMapping(value = "/feign/system/sysMenuRole/updateById")
    int updateById(@RequestBody SysMenuRoleQO qo);

    @RequestMapping(value = "/feign/system/sysMenuRole/getById")
    SysMenuRoleVO getById(@RequestBody Long id);

}
