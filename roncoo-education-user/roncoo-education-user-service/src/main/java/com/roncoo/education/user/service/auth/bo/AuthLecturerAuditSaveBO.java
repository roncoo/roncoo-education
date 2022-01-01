package com.roncoo.education.user.service.auth.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 讲师信息-审核
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class AuthLecturerAuditSaveBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 讲师名称
     */
    @ApiModelProperty(value = "讲师名称", required = true)
    private String lecturerName;
    /**
     * 电话
     */
    @ApiModelProperty(value = "电话", required = true)
    private String lecturerMobile;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱", required = true)
    private String lecturerEmail;
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像", required = false)
    private String headImgUrl;
    /**
     * 简介
     */
    @ApiModelProperty(value = "简介", required = false)
    private String introduce;
    /**
     * 职位
     */
    @ApiModelProperty(value = "职位", required = false)
    private String position;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", required = true)
    private String mobilePsw;
    /**
     * 重复密码
     */
    @ApiModelProperty(value = "重复密码", required = true)
    private String repassword;
    /**
     * 手机验证码
     */
    @ApiModelProperty(value = "手机验证码", required = false)
    private String code;
    /**
     * clientId
     */
    @ApiModelProperty(value = "clientId", required = false)
    private String clientId;
}
