package com.roncoo.education.system.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.roncoo.education.system.common.interfaces.BossSysMenuRole;

/**
 * 菜单角色关联表 
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-system-service")
public interface IBossSysMenuRole extends BossSysMenuRole {

}
