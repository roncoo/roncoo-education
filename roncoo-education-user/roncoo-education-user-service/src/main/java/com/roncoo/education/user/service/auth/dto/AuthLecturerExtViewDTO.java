package com.roncoo.education.user.service.auth.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 用户教育信息
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class AuthLecturerExtViewDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 讲师用户编号
     */
    @ApiModelProperty(value = "讲师用户编号")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long lecturerUserNo;
    /**
     * 总收入
     */
    @ApiModelProperty(value = "总收入")
    private BigDecimal totalIncome;
    /**
     * 可提余额
     */
    @ApiModelProperty(value = "可提余额")
    private BigDecimal enableBalances;
    /**
     * 讲师名称
     */
    @ApiModelProperty(value = "讲师名称")
    private String lecturerName;
    /**
     * 讲师头像
     */
    @ApiModelProperty(value = "讲师头像")
    private String headImgUrl;
    /**
     * 银行卡号
     */
    @ApiModelProperty(value = "银行卡号")
    private String bankCardNo;
    /**
     * 开户支行
     */
    @ApiModelProperty(value = "开户支行")
    private String bankBranchName;
    /**
     * 开户人名称
     */
    @ApiModelProperty(value = "开户人名称")
    private String bankUserName;
    /**
     * 银行名称
     */
    @ApiModelProperty(value = "银行名称")
    private String bankName;
}
