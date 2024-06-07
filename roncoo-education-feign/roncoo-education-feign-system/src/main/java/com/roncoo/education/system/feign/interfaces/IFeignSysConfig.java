package com.roncoo.education.system.feign.interfaces;

import com.roncoo.education.common.sms.Sms;
import com.roncoo.education.system.feign.interfaces.vo.*;
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
     * 登录
     *
     * @return
     */
    @GetMapping(value = "/getLogin")
    LoginConfig getLogin();

    /**
     * 视频云配置(录播）
     *
     * @return
     */
    @GetMapping(value = "/getVideo")
    VideoConfig getVideo();

    /**
     * 文档存储配置
     *
     * @return
     */
    @GetMapping(value = "/getDoc")
    DocConfig getDoc();

    /**
     * 短信配置
     *
     * @return
     */
    @GetMapping(value = "/getSms")
    Sms getSms();

    /**
     * 支付配置
     *
     * @return
     */
    @GetMapping(value = "/getPay")
    PayConfig getPay();
}
