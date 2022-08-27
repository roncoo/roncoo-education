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
 * @date 2022-08-27
 */
@Data
@Accessors(chain = true)
public class OrderInfoPageVO implements Serializable{

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
     * 课程ID
     */
    private Long courseId;

    /**
     * 划线价
     */
    private BigDecimal rulingPrice;

    /**
     * 课程价格
     */
    private BigDecimal coursePrice;

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
