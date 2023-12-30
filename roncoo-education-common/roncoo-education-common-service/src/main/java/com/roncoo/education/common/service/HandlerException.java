package com.roncoo.education.common.service;

import com.roncoo.education.common.core.base.BaseException;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.JSUtil;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.StringJoiner;

/**
 * @author wujing
 */
@RestControllerAdvice(basePackages = {"com.roncoo.education.course.service", "com.roncoo.education.system.service", "com.roncoo.education.user.service"})
public class HandlerException extends BaseController {

    @ExceptionHandler({FeignException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> processBizException(FeignException e) {
        String msg = e.getMessage();
        if (!StringUtils.hasText(msg)) {
            return Result.error("服务繁忙，请重试");
        }
        log.error("FeignException, {}", msg);
        Map<String, Object> m = JSUtil.parseArray(msg.substring(msg.lastIndexOf("[{")), Map.class).get(0);
        if (null != m.get("message")) {
            return Result.error(m.get("message").toString());
        }
        return Result.error("服务繁忙，请重试");
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.OK)
    public Result<String> processException(MethodArgumentNotValidException ex) {
        StringJoiner joiner = new StringJoiner("、");
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            joiner.add(fieldError.getDefaultMessage());
        }
        log.error("MethodArgumentNotValidException, {}", joiner);
        return Result.error(joiner.toString());
    }

    @ExceptionHandler({BaseException.class})
    @ResponseStatus(HttpStatus.OK)
    public Result<String> processException(BaseException e) {
        log.error("BaseException", e);
        return Result.error(e.getMessage());
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.OK)
    public Result<String> processException(Exception e) {
        log.error("Exception", e);
        return Result.error("服务繁忙，请重试");
    }

}
