package com.roncoo.education.user.feign.interfaces.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 订单信息表
 *
 * @author wujing
 * @date 2022-08-25
 */
@Data
@Accessors(chain = true)
public class OrderInfoViewVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;

    /**
     * 订单号
     */
    private Long orderNo;

    /**
     * 讲师名称
     */
    private String lecturerName;

    /**
     * 下单用户编号
     */
    private Long userId;

    /**
     * 下单用户电话
     */
    private String mobile;

    /**
     * 下单用户注册时间
     */
    private LocalDateTime registerTime;

    /**
     * 讲师ID
     */
    private Long lecturerId;

    /**
     * 课程ID
     */
    private Long courseId;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 应付金额
     */
    private BigDecimal pricePayable;

    /**
     * 优惠金额
     */
    private BigDecimal priceDiscount;

    /**
     * 实付金额
     */
    private BigDecimal pricePaid;

    /**
     * 交易类型：1线上支付，2线下支付
     */
    private Integer tradeType;

    /**
     * 支付方式：1微信支付，2支付宝支付
     */
    private Integer payType;

    /**
     * 购买渠道：1web
     */
    private Integer channelType;

    /**
     * 订单状态：1待支付，2成功支付，3支付失败，4已关闭
     */
    private Integer orderStatus;

    /**
     * 客户备注
     */
    private String remarkCus;

    /**
     * 后台备注
     */
    private String remark;

    /**
     * 支付时间
     */
    private LocalDateTime payTime;
}
