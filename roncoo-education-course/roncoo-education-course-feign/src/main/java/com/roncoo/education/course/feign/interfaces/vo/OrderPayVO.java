package com.roncoo.education.course.feign.interfaces.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单支付信息表
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class OrderPayVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 订单号
     */
    private Long orderNo;
    /**
     * 流水号
     */
    private Long serialNumber;
    /**
     * 订单状态：1待支付，2成功支付，3支付失败，4已关闭，5已退款, 6订单解绑
     */
    private Integer orderStatus;
    /**
     * 支付方式：1微信支付，2支付宝支付，3积分支付，4手工录单
     */
    private Integer payType;
    /**
     * 支付时间
     */
    private Date payTime;

}
