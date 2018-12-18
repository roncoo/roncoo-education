package com.roncoo.education.user.feign.web;

import org.springframework.cloud.openfeign.FeignClient;

import com.roncoo.education.user.common.interfaces.web.BossLecturerProfit;

/**
 * 讲师提现日志表 
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-user-service")
public interface IBossLecturerProfit extends BossLecturerProfit {

}
