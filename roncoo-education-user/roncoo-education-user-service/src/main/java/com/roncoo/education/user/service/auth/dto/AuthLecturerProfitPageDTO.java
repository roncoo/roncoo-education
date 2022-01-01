package com.roncoo.education.user.service.auth.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 讲师提现日志表
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class AuthLecturerProfitPageDTO implements Serializable {

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
    private Date gmtCreate;
    /**
     * 讲师用户编号
     */
    @ApiModelProperty(value = "讲师用户编号")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long lecturerUserNo;
    /**
     * 银行卡号
     */
    @ApiModelProperty(value = "银行卡号")
    private String bankCardNo;
    /**
     * 银行名称
     */
    @ApiModelProperty(value = "银行名称")
    private String bankName;
    /**
     * 银行支行名称
     */
    @ApiModelProperty(value = "银行支行名称")
    private String bankBranchName;
    /**
     * 银行开户名
     */
    @ApiModelProperty(value = "银行开户名")
    private String bankUserName;
    /**
     * 银行身份证号
     */
    @ApiModelProperty(value = "银行身份证号")
    private String bankIdCardNo;
    /**
     * 讲师收入
     */
    @ApiModelProperty(value = "讲师收入")
    private BigDecimal lecturerProfit;
    /**
     * 平台收入
     */
    @ApiModelProperty(value = "平台收入")
    private BigDecimal platformProfit;
    /**
     * 分润状态（1确认中，2成功，3失败）
     */
    @ApiModelProperty(value = "分润状态（1确认中，2成功，3失败）")
    private Integer profitStatus;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
