package com.roncoo.education.common.service;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.BaseException;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.JSUtil;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

/**
 * @author wujing
 */
@RestControllerAdvice(basePackages = {"com.roncoo.education.course.service", "com.roncoo.education.system.service", "com.roncoo.education.user.service"})
public class HandlerException extends BaseController {

    @ExceptionHandler({FeignException.class})
    @ResponseStatus(HttpStatus.OK)
    public Result<String> processBizException(FeignException e) {
        String msg = e.getCause().toString().split("\n|\r\n|\r")[1];
        logger.error(msg);
        @SuppressWarnings("unchecked")
        Map<String, Object> m = JSUtil.parseObject(msg, Map.class);
        if (null != m.get("message")) {
            return Result.error(m.get("message").toString());
        }
        return Result.error("服务繁忙，请重试");
    }

    @ExceptionHandler({BaseException.class})
    @ResponseStatus(HttpStatus.OK)
    public Result<String> processException(BaseException e) {
        logger.error("BaseException", e);
        return Result.error(e.getMessage());
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.OK)
    public Result<String> processException(Exception e) {
        logger.error("Exception", e);
        return Result.error("服务繁忙，请重试");
    }

}
