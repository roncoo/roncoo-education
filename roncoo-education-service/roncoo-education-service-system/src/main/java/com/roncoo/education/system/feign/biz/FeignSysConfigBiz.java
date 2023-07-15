package com.roncoo.education.system.feign.biz;

import com.roncoo.education.common.core.sms.SmsConfig;
import com.roncoo.education.common.pay.util.AliPayConfig;
import com.roncoo.education.common.pay.util.WxPayConfig;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.system.feign.interfaces.vo.PayConfig;
import com.roncoo.education.system.feign.interfaces.vo.SysConfig;
import com.roncoo.education.system.feign.interfaces.vo.VodConfig;
import com.roncoo.education.system.service.biz.SysConfigCommonBiz;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

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

    public SmsConfig getSms() {
        return sysConfigCommonBiz.getSysConfig(SmsConfig.class);
    }

    public VodConfig getVod() {
        return sysConfigCommonBiz.getSysConfig(VodConfig.class);
    }


}
