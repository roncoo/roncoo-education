package com.roncoo.education.user.feign.web;

import org.springframework.cloud.openfeign.FeignClient;

import com.roncoo.education.user.common.interfaces.web.BossUserExt;

/**
 * 用户教育信息
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-user-service")
public interface IBossUserExt extends BossUserExt {

}
