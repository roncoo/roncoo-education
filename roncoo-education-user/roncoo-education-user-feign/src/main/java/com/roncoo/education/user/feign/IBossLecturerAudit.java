package com.roncoo.education.user.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.roncoo.education.user.common.interfaces.BossLecturerAudit;

/**
 * 讲师信息-审核 
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-user-service")
public interface IBossLecturerAudit extends BossLecturerAudit {

}
