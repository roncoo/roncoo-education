package com.roncoo.education.user.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.roncoo.education.user.common.interfaces.BossLecturerExt;

/**
 * 讲师账户信息表 
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-user-service")
public interface IBossLecturerExt extends BossLecturerExt {

}
