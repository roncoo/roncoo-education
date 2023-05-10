package com.roncoo.education.user.service.admin.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * ADMIN-订单支付信息表
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "ADMIN-订单支付信息表分页")
public class AdminOrderPayPageReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "订单号")
    private Long orderNo;

    @ApiModelProperty(value = "流水号")
    private Long serialNumber;

    @ApiModelProperty(value = "划线价")
    private BigDecimal rulingPrice;

    @ApiModelProperty(value = "课程价格")
    private BigDecimal coursePrice;

    @ApiModelProperty(value = "支付方式：1微信支付，2支付宝支付，3积分支付，4手工录单")
    private Integer payType;

    @ApiModelProperty(value = "订单状态：1待支付，2成功支付，3支付失败，4已关闭，5已退款, 6订单解绑")
    private Integer orderStatus;

    @ApiModelProperty(value = "客户备注")
    private String remarkCus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "支付时间")
    private LocalDateTime payTime;

    @ApiModelProperty(value = "当前页")
    private int pageCurrent = 1;

    @ApiModelProperty(value = "每页条数")
    private int pageSize = 20;
}
