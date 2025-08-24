package com.roncoo.education.system.feign;

import com.roncoo.education.system.feign.biz.FeignSysLogBiz;
import com.roncoo.education.system.feign.interfaces.IFeignSysLog;
import com.roncoo.education.system.feign.interfaces.qo.FeignSysLogQO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.NotNull;

/**
 * 日志接口
 *
 * @author wujing
 * @date 2022-08-25
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/sys/log")
public class FeignSysLogController implements IFeignSysLog {

    @NotNull
    private final FeignSysLogBiz biz;

    @Override
    public int save(@RequestBody FeignSysLogQO qo) {
        return biz.save(qo);
    }
}
