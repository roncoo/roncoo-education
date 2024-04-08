package com.roncoo.education.system.service.admin.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 后台操作日志表
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AdminSysLogPageResp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtCreate;
    /**
     * 操作人
     */
    @ApiModelProperty(value = "操作人")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;
    /**
     * 用户操作
     */
    @ApiModelProperty(value = "用户操作")
    private String operation;
    /**
     * 请求方法
     */
    @ApiModelProperty(value = "请求方法")
    private String method;
    /**
     * 请求路径
     */
    @ApiModelProperty(value = "请求路径")
    private String path;
    /**
     * 请求参数
     */
    @ApiModelProperty(value = "请求参数")
    private String content;
    /**
     * IP地址
     */
    @ApiModelProperty(value = "IP地址")
    private String loginIp;

    private String country;

    private String province;

    private String city;

    private String browser;

    private String os;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;
    /**
     * 真实姓名
     */
    @ApiModelProperty(value = "真实姓名")
    private String realName;
}
