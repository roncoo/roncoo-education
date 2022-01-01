package com.roncoo.education.course.service.pc.resq;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单支付信息表-分页
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class OrderPayPageRESQ implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;
    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long orderNo;
    /**
     * 流水号
     */
    @ApiModelProperty(value = "流水号")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long serialNumber;
    /**
     * 订单状态：1待支付，2成功支付，3支付失败，4已关闭，5已退款, 6订单解绑
     */
    @ApiModelProperty(value = "订单状态：1待支付，2成功支付，3支付失败，4已关闭，5已退款, 6订单解绑")
    private Integer orderStatus;
    /**
     * 支付方式：1微信支付，2支付宝支付，3积分支付，4手工录单
     */
    @ApiModelProperty(value = "支付方式：1微信支付，2支付宝支付，3积分支付，4手工录单")
    private Integer payType;
    /**
     * 支付时间
     */
    @ApiModelProperty(value = "支付时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;

}
