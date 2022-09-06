package com.roncoo.education.user.feign.interfaces.qo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单支付信息表
 *
 * @author wujing
 * @date 2022-09-06
 */
@Data
@Accessors(chain = true)
public class OrderPaySaveQO implements Serializable{

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
     * 订单号
     */
    private Long orderNo;

    /**
     * 流水号
     */
    private Long serialNumber;

    /**
     * 划线价
     */
    private BigDecimal rulingPrice;

    /**
     * 课程价格
     */
    private BigDecimal coursePrice;

    /**
     * 支付方式：1微信支付，2支付宝支付，3积分支付，4手工录单
     */
    private Integer payType;

    /**
     * 订单状态：1待支付，2成功支付，3支付失败，4已关闭，5已退款, 6订单解绑
     */
    private Integer orderStatus;

    /**
     * 客户备注
     */
    private String remarkCus;

    /**
     * 支付时间
     */
    private LocalDateTime payTime;

}
