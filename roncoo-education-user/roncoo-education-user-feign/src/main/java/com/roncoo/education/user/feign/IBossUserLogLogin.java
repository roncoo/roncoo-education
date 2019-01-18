package com.roncoo.education.user.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.roncoo.education.user.common.interfaces.BossUserLogLogin;

/**
 * 用户错误登录日志 
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-user-service")
public interface IBossUserLogLogin extends BossUserLogLogin {

}
