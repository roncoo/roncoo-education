package com.roncoo.education.course.service.auth.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单支付信息表
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AuthOrderPayDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String orderNo;

    private String payMessage;

    private String courseName;

    private BigDecimal price;

    private Integer payType;

}
