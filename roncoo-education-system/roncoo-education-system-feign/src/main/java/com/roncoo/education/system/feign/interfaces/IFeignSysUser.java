package com.roncoo.education.system.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.system.feign.interfaces.qo.SysUserQO;
import com.roncoo.education.system.feign.interfaces.vo.SysUserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台用户信息
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-system-service")
public interface IFeignSysUser {


    @RequestMapping(value = "/feign/system/sysUser/listForPage")
    Page<SysUserVO> listForPage(@RequestBody SysUserQO qo);

    @RequestMapping(value = "/feign/system/sysUser/save")
    int save(@RequestBody SysUserQO qo);

    @RequestMapping(value = "/feign/system/sysUser/deleteById")
    int deleteById(@RequestBody Long id);

    @RequestMapping(value = "/feign/system/sysUser/updateById")
    int updateById(@RequestBody SysUserQO qo);

    @RequestMapping(value = "/feign/system/sysUser/getById")
    SysUserVO getById(@RequestBody Long id);

}
