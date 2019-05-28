package com.roncoo.education.system.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.roncoo.education.system.common.interfaces.BossSysUser;

/**
 * 后台用户信息 
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-system-service")
public interface IBossSysUser extends BossSysUser {

}
