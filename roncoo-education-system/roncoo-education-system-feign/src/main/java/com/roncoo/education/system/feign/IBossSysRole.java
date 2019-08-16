package com.roncoo.education.system.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.roncoo.education.system.common.interfaces.BossSysRole;

/**
 * 角色信息 
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-system-service")
public interface IBossSysRole extends BossSysRole {

}
