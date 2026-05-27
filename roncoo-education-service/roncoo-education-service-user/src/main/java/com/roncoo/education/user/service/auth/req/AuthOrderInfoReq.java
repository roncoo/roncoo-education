package com.roncoo.education.user.service.auth.req;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "API-AUTH-订单信息表")
public class AuthOrderInfoReq implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    @Schema(description = "当前页")
    private int pageCurrent = 1;
    /**
     * 每页记录数
     */
    @Schema(description = "每页条数")
    private int pageSize = 20;

    @Schema(description = "订单号")
    private Long orderNo;

    @Schema(description = "交易类型：1线上支付，2线下支付")
    private Integer tradeType;

    @Schema(description = "支付方式：1微信支付，2支付宝支付")
    private Integer payType;

    @Schema(description = "购买渠道：1web")
    private Integer channelType;

    @Schema(description = "订单状态：1待支付，2成功支付，3支付失败，4已关闭")
    private Integer orderStatus;

    @Schema(description = "客户备注")
    private String remarkCus;
}
