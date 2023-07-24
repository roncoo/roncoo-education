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
@FeignClient(value = "service-system", path = "/system/sys/config")
public interface IFeignSysConfig {

    /**
     * 系统配置
     *
     * @return
     */
    @GetMapping(value = "/getSys")
    SysConfig getSys();

    /**
     * 支付配置
     *
     * @return
     */
    @GetMapping(value = "/getPay")
    PayConfig getPay();

    /**
     * 短信配置
     *
     * @return
     */
    @GetMapping(value = "/getSms")
    SmsConfig getSms();

    /**
     * 视频云配置
     *
     * @return
     */
    @GetMapping(value = "/getVod")
    VodConfig getVod();
}
