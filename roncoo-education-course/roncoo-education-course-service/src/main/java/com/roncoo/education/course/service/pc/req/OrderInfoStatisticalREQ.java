package com.roncoo.education.course.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 订单信息表-统计
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class OrderInfoStatisticalREQ implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页", required = true)
    private int pageCurrent = 1;
    /**
     * 每页记录数
     */
    @ApiModelProperty(value = "每页记录数", required = true)
    private int pageSize = 20;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", required = false)
    private Long id;
    /**
     * 讲师用户编号
     */
    @ApiModelProperty(value = "讲师用户编号", required = false)
    private Long lecturerUserNo;
    /**
     * 讲师名称
     */
    @ApiModelProperty(value = "讲师名称", required = false)
    private String lecturerName;
    /**
     * 用户电话
     */
    @ApiModelProperty(value = "用户电话", required = false)
    private String mobile;
    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号", required = false)
    private Long orderNo;
    /**
     * 课程ID
     */
    @ApiModelProperty(value = "课程ID", required = false)
    private Long courseId;
    /**
     * 课程名称
     */
    @ApiModelProperty(value = "课程名称", required = false)
    private String courseName;
    /**
     * 交易类型：1线上支付，2线下支付
     */
    @ApiModelProperty(value = "交易类型：1线上支付，2线下支付", required = false)
    private Integer tradeType;
    /**
     * 支付方式：1微信支付，2支付宝支付，3积分支付，4手工录单
     */
    @ApiModelProperty(value = "支付方式：1微信支付，2支付宝支付，3积分支付，4手工录单", required = false)
    private Integer payType;
    /**
     * 购买渠道：1web，2pp，3微信，4手工绑定
     */
    @ApiModelProperty(value = "购买渠道：1web，2pp，3微信，4手工绑定", required = false)
    private Integer channelType;
    /**
     * 订单状态：1待支付，2成功支付，3支付失败，4已关闭，5已退款，6订单解绑
     */
    @ApiModelProperty(value = "订单状态：1待支付，2成功支付，3支付失败，4已关闭，5已退款，6订单解绑", required = false)
    private Integer orderStatus;
    /**
     * 支付开始时间
     */
    @ApiModelProperty(value = "支付开始时间", required = false)
    private String beginPayTime;
    /**
     * 支付结束时间
     */
    @ApiModelProperty(value = "支付结束时间", required = false)
    private String endPayTime;
    /**
     * 后台备注
     */
    @ApiModelProperty(value = "后台备注", required = false)
    private String remark;

}
