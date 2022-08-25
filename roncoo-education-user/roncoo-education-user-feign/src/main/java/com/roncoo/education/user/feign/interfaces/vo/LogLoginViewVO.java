package com.roncoo.education.user.feign.interfaces.vo;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户登录日志
 *
 * @author wujing
 * @date 2022-08-25
 */
@Data
@Accessors(chain = true)
public class LogLoginViewVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 登录状态(1成功，0失败)
     */
    private Integer loginStatus;

    /**
     * 登录IP
     */
    private String loginIp;

    /**
     * 国家
     */
    private String country;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;
}
