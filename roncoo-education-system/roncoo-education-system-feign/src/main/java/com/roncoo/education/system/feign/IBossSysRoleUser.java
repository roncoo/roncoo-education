package com.roncoo.education.system.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.roncoo.education.system.common.interfaces.BossSysRoleUser;

/**
 * 角色用户关联表 
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-system-service")
public interface IBossSysRoleUser extends BossSysRoleUser {

}
