package com.roncoo.education.user.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.roncoo.education.user.common.interfaces.BossSendSmsLog;

/**
 * 用户发送短信日志
 *
 * @author YZJ
 */
@FeignClient(value = "roncoo-education-user-service")
public interface IBossSendSmsLog extends BossSendSmsLog {

}
