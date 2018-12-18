package com.roncoo.education.user.feign.web;

import org.springframework.cloud.openfeign.FeignClient;

import com.roncoo.education.user.common.interfaces.web.BossUser;

/**
 * 用户基本信息
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-user-service")
public interface IBossUser extends BossUser {

}
