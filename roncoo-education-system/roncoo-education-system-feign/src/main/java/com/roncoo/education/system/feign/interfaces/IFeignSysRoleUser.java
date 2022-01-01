package com.roncoo.education.system.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.system.feign.interfaces.qo.SysRoleUserQO;
import com.roncoo.education.system.feign.interfaces.vo.SysRoleUserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 角色用户关联表
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-system-service")
public interface IFeignSysRoleUser {


    @RequestMapping(value = "/feign/system/sysRoleUser/listForPage")
    Page<SysRoleUserVO> listForPage(@RequestBody SysRoleUserQO qo);

    @RequestMapping(value = "/feign/system/sysRoleUser/save")
    int save(@RequestBody SysRoleUserQO qo);

    @RequestMapping(value = "/feign/system/sysRoleUser/deleteById")
    int deleteById(@RequestBody Long id);

    @RequestMapping(value = "/feign/system/sysRoleUser/updateById")
    int updateById(@RequestBody SysRoleUserQO qo);

    @RequestMapping(value = "/feign/system/sysRoleUser/getById")
    SysRoleUserVO getById(@RequestBody Long id);

}
