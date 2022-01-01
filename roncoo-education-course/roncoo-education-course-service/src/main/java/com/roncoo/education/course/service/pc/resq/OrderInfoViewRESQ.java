package com.roncoo.education.course.service.pc.resq;

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
 * 订单信息表-查看
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class OrderInfoViewRESQ implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 讲师名称
     */
    @ApiModelProperty(value = "讲师名称")
    private String lecturerName;
    /**
     * 用户电话
     */
    @ApiModelProperty(value = "用户电话")
    private String mobile;
    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userNo;
    /**
     * 用户注册时间
     */
    @ApiModelProperty(value = "用户注册时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registerTime;
    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单编号")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long orderNo;
    /**
     * 课程名称
     */
    @ApiModelProperty(value = "课程名称")
    private String courseName;
    /**
     * 优惠金额
     */
    @ApiModelProperty(value = "优惠金额")
    private BigDecimal priceDiscount;
    /**
     * 实付金额
     */
    @ApiModelProperty(value = "实际支付金额")
    private BigDecimal pricePaid;
    /**
     * 平台收入
     */
    @ApiModelProperty(value = "平台收入")
    private BigDecimal platformProfit;
    /**
     * 讲师收入
     */
    @ApiModelProperty(value = "讲师收入")
    private BigDecimal lecturerProfit;
    /**
     * 交易类型：1线上支付，2线下支付
     */
    @ApiModelProperty(value = "交易类型：1线上支付，2线下支付")
    private Integer tradeType;
    /**
     * 支付方式：1微信支付，2支付宝支付
     */
    @ApiModelProperty(value = "支付方式(1:微信支付，2:支付宝支付)")
    private Integer payType;
    /**
     * 购买渠道：1:WEB
     */
    @ApiModelProperty(value = "购买渠道：1:WEB")
    private Integer channelType;
    /**
     * 订单状态：0待支付，1成功支付，2支付失败，3已关闭，4已退款
     */
    @ApiModelProperty(value = "订单状态(1:待支付，2:支付成功，3:支付失败，4:已关闭)")
    private Integer orderStatus;
    /**
     * 客户备注
     */
    @ApiModelProperty(value = "客户备注")
    private String remarkCus;
    /**
     * 支付时间
     */
    @ApiModelProperty(value = "支付时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;
    /**
     * 后台备注
     */
    @ApiModelProperty(value = "后台备注")
    private String remark;
    /**
     * 流水号
     */
    @ApiModelProperty(value = "流水号")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long serialNumber;

}
