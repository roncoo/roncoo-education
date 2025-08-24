package com.roncoo.education.system.feign.biz;

import com.roncoo.education.common.pay.util.AliPayConfig;
import com.roncoo.education.common.pay.util.WxPayConfig;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.common.sms.Sms;
import com.roncoo.education.system.feign.interfaces.vo.*;
import com.roncoo.education.system.service.biz.SysConfigCommonBiz;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotNull;

/**
 * 系统配置
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class FeignSysConfigBiz extends BaseBiz {

    @NotNull
    private final SysConfigCommonBiz sysConfigCommonBiz;

    public SysConfig getSys() {
        return sysConfigCommonBiz.getSysConfig(SysConfig.class);
    }

    public PayConfig getPay() {
        PayConfig payConfig = new PayConfig();
        payConfig.setAliPayConfig(sysConfigCommonBiz.getSysConfig(AliPayConfig.class));
        payConfig.setWxPayConfig(sysConfigCommonBiz.getSysConfig(WxPayConfig.class));
        return payConfig;
    }

    public Sms getSms() {
        return sysConfigCommonBiz.getSysConfig(Sms.class);
    }

    public VideoConfig getVideo() {
        return sysConfigCommonBiz.getSysConfig(VideoConfig.class);
    }


    public DocConfig getDoc() {
        return sysConfigCommonBiz.getSysConfig(DocConfig.class);
    }

    public LoginConfig getLogin() {
        return sysConfigCommonBiz.getSysConfig(LoginConfig.class);
    }
}
