package com.roncoo.education.user.service.pc.resq;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 讲师账户信息表
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class LecturerExtViewRESQ implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 总收入
     */
    @ApiModelProperty(value = "总收入")
    private BigDecimal totalIncome;
    /**
     * 已提金额
     */
    @ApiModelProperty(value = "已提金额")
    private BigDecimal historyMoney;
    /**
     * 可提余额
     */
    @ApiModelProperty(value = "可提余额")
    private BigDecimal enableBalances;
    /**
     * 冻结金额
     */
    @ApiModelProperty(value = "冻结金额")
    private BigDecimal freezeBalances;
    /**
     * sign
     */
    @ApiModelProperty(value = "sign")
    private String sign;
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

}
