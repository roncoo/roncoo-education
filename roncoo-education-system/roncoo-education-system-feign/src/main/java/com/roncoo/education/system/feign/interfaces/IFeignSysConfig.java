package com.roncoo.education.system.feign.interfaces;

import com.roncoo.education.common.core.aliyun.Aliyun;
import com.roncoo.education.system.feign.interfaces.vo.SysConfigViewVO;
import com.roncoo.education.system.feign.interfaces.vo.VodConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

/**
 * 系统配置 接口
 *
 * @author wujing
 * @date 2022-08-25
 */
@FeignClient(value = "system-service", path = "/system/sys/config")
public interface IFeignSysConfig {

    @GetMapping(value = "/getMapByConfigType/{type}")
    Map<String, String> getMapByConfigType(@PathVariable(value = "type") Integer configType);

    @GetMapping(value = "/getAliyun")
    Aliyun getAliyun();

    @GetMapping(value = "/getVod")
    VodConfig getVod();

    @GetMapping(value = "/getByConfigKey/{key}")
    SysConfigViewVO getByConfigKey(@PathVariable(value = "key") String configKey);
}
