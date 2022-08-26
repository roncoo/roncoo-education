/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.common.core.base;

import com.roncoo.education.common.core.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;


/**
 * 接口返回对象实体
 *
 * @param <T>
 * @author wujing
 */
@Slf4j
public final class Result<T> implements Serializable {


    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private Integer code = ResultEnum.ERROR.getCode();

    /**
     * 错误信息
     */
    private String msg = null;

    /**
     * 返回结果实体
     */
    private T data = null;

    public Result() {
    }

    private Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> error(String msg) {
        log.debug("返回错误：code={}, msg={}", ResultEnum.ERROR.getCode(), msg);
        return new Result<T>(ResultEnum.ERROR.getCode(), msg, null);
    }

    public static <T> Result<T> error(ResultEnum resultEnum) {
        log.debug("返回错误：code={}, msg={}", resultEnum.getCode(), resultEnum.getDesc());
        return new Result<T>(resultEnum.getCode(), resultEnum.getDesc(), null);
    }

    public static <T> Result<T> error(int code, String msg) {
        log.debug("返回错误：code={}, msg={}", code, msg);
        return new Result<T>(code, msg, null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(ResultEnum.SUCCESS.getCode(), "", data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result [code=" + code + ", msg=" + msg + ", data=" + data + "]";
    }

}
