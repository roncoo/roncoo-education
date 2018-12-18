package com.roncoo.education.user.feign.web;

import org.springframework.cloud.openfeign.FeignClient;

import com.roncoo.education.user.common.interfaces.web.BossUserLogModified;

/**
 * 用户修改日志 
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-user-service")
public interface IBossUserLogModified extends BossUserLogModified {

}
