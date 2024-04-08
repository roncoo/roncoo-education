package com.roncoo.education.system.feign.interfaces;

import com.roncoo.education.system.feign.interfaces.qo.FeignSysLogQO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 系统配置 接口
 *
 * @author wujing
 * @date 2022-08-25
 */
@FeignClient(value = "service-system", path = "/system/sys/log")
public interface IFeignSysLog {

    @PostMapping(value = "/save")
    int save(FeignSysLogQO qo);

}
