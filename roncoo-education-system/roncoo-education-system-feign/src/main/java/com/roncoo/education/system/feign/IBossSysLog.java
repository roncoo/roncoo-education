package com.roncoo.education.system.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.roncoo.education.system.common.interfaces.BossSysLog;

/**
 * 后台操作日志表 
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-system-service")
public interface IBossSysLog extends BossSysLog {

}
