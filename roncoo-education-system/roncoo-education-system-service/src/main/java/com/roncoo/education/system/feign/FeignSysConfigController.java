package com.roncoo.education.system.feign;

import com.roncoo.education.common.core.sms.SmsConfig;
import com.roncoo.education.system.feign.biz.FeignSysConfigBiz;
import com.roncoo.education.system.feign.interfaces.IFeignSysConfig;
import com.roncoo.education.system.feign.interfaces.vo.SysConfigViewVO;
import com.roncoo.education.system.feign.interfaces.vo.VodConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Map;

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
    public Map<String, String> getMapByConfigType(@PathVariable(value = "type") Integer configType) {
        return biz.getMapByConfigType(configType);
    }

    @Override
    public SmsConfig getSms() {
        return biz.getSms();
    }

    @Override
    public VodConfig getVod() {
        return biz.getVod();
    }

    @Override
    public SysConfigViewVO getByConfigKey(@PathVariable(value = "key") String configKey) {
        return biz.getByConfigKey(configKey);
    }
}
