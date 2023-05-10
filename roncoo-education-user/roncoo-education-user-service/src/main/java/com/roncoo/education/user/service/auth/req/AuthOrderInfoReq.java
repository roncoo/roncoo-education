package com.roncoo.education.user.service.auth.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * API-AUTH-订单信息表
 * </p>
 *
 * @author wujing
 * @date 2022-08-25
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "API-AUTH-订单信息表")
public class AuthOrderInfoReq implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页")
    private int pageCurrent = 1;
    /**
     * 每页记录数
     */
    @ApiModelProperty(value = "每页条数")
    private int pageSize = 20;

    @ApiModelProperty(value = "订单号")
    private Long orderNo;

    @ApiModelProperty(value = "交易类型：1线上支付，2线下支付")
    private Integer tradeType;

    @ApiModelProperty(value = "支付方式：1微信支付，2支付宝支付")
    private Integer payType;

    @ApiModelProperty(value = "购买渠道：1web")
    private Integer channelType;

    @ApiModelProperty(value = "订单状态：1待支付，2成功支付，3支付失败，4已关闭")
    private Integer orderStatus;

    @ApiModelProperty(value = "客户备注")
    private String remarkCus;
}
