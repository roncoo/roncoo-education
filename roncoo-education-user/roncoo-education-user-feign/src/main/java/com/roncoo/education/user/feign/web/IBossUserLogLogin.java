package com.roncoo.education.user.feign.web;

import org.springframework.cloud.openfeign.FeignClient;

import com.roncoo.education.user.common.interfaces.web.BossUserLogLogin;

/**
 * 用户错误登录日志 
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-user-service")
public interface IBossUserLogLogin extends BossUserLogLogin {

}
