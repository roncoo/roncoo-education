package com.roncoo.education.system.feign;

import com.roncoo.education.common.core.sms.SmsConfig;
import com.roncoo.education.system.feign.biz.FeignSysConfigBiz;
import com.roncoo.education.system.feign.interfaces.IFeignSysConfig;
import com.roncoo.education.system.feign.interfaces.vo.PayConfig;
import com.roncoo.education.system.feign.interfaces.vo.SysConfig;
import com.roncoo.education.system.feign.interfaces.vo.VodConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * 系统配置
 *
 * @author wujing
 * @date 2022-08-25
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/sys/config")
public class FeignSysConfigController implements IFeignSysConfig {

    @NotNull
    private final FeignSysConfigBiz biz;

    @Override
    public SysConfig getSys() {
        return biz.getSys();
    }

    @Override
    public PayConfig getPay() {
        return biz.getPay();
    }

    @Override
    public SmsConfig getSms() {
        return biz.getSms();
    }

    @Override
    public VodConfig getVod() {
        return biz.getVod();
    }

}
