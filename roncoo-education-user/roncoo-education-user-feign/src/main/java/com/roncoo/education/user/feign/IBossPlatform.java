package com.roncoo.education.user.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.roncoo.education.user.common.interfaces.BossPlatform;

/**
 * 平台信息
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-user-service")
public interface IBossPlatform extends BossPlatform {

}
