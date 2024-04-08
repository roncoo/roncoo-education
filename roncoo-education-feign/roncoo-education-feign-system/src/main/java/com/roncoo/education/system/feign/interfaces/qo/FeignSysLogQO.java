package com.roncoo.education.system.feign.interfaces.qo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 后台操作日志
 *
 * @author LYQ
 * @date 2021-12-17
 */
@Data
@Accessors(chain = true)
public class FeignSysLogQO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 用户操作
     */
    private String operation;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求路径
     */
    private String path;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

}
