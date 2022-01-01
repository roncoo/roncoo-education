package com.roncoo.education.user.service.auth.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用户教育信息
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class AuthUserExtBankBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号", required = true)
    private Long lecturerUserNo;
    /**
     * 银行卡号
     */
    @ApiModelProperty(value = "银行卡号", required = true)
    private String bankCardNo;
    /**
     * 银行名称
     */
    @ApiModelProperty(value = "银行名称", required = true)
    private String bankName;
    /**
     * 银行支行名称
     */
    @ApiModelProperty(value = "银行支行名称", required = true)
    private String bankBranchName;
    /**
     * 银行开户名
     */
    @ApiModelProperty(value = "银行开户名", required = true)
    private String bankUserName;
    /**
     * 银行身份证号
     */
    @ApiModelProperty(value = "银行身份证号", required = true)
    private String bankIdCardNo;
    /**
     * 验证码
     */
    @ApiModelProperty(value = "验证码", required = true)
    private String smsCode;
    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码", required = true)
    private String mobile;
    /**
     * clientId
     */
    @ApiModelProperty(value = "clientId", required = true)
    private String clientId;

}
