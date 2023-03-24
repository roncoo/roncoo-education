package com.roncoo.education.system.feign.interfaces;

import com.roncoo.education.common.core.sms.SmsConfig;
import com.roncoo.education.system.feign.interfaces.vo.PayConfig;
import com.roncoo.education.system.feign.interfaces.vo.SysConfig;
import com.roncoo.education.system.feign.interfaces.vo.VodConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 系统配置 接口
 *
 * @author wujing
 * @date 2022-08-25
 */
@FeignClient(value = "system-service", path = "/system/sys/config")
public interface IFeignSysConfig {

    @GetMapping(value = "/getSys")
    SysConfig getSys();

    @GetMapping(value = "/getPay")
    PayConfig getPay();

    @GetMapping(value = "/getSms")
    SmsConfig getSms();

    @GetMapping(value = "/getVod")
    VodConfig getVod();
}
