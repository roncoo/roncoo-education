package com.roncoo.education.app.gateway;

import com.roncoo.education.common.core.base.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;

/**
 * @author wujing
 */
@Slf4j
@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Map<String, Object> map = super.getErrorAttributes(request, options);
        log.error("系统异常，{}", map);
        map.put("status", HttpStatus.OK.value());
        Throwable error = this.getError(request);
        if (error instanceof BaseException) {
            BaseException be = (BaseException) error;
            map.put("code", be.getCode());
            map.put("msg", be.getMessage());
        }
        return map;
    }
}
