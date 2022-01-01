package com.roncoo.education.system.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.system.feign.interfaces.qo.SysRoleQO;
import com.roncoo.education.system.feign.interfaces.vo.SysRoleVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 角色信息
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-system-service")
public interface IFeignSysRole {

    @RequestMapping(value = "/feign/system/sysRole/listForPage")
    Page<SysRoleVO> listForPage(@RequestBody SysRoleQO qo);

    @RequestMapping(value = "/feign/system/sysRole/save")
    int save(@RequestBody SysRoleQO qo);

    @RequestMapping(value = "/feign/system/sysRole/deleteById")
    int deleteById(@RequestBody Long id);

    @RequestMapping(value = "/feign/system/sysRole/updateById")
    int updateById(@RequestBody SysRoleQO qo);

    @RequestMapping(value = "/feign/system/sysRole/getById")
    SysRoleVO getById(@RequestBody Long id);

}
