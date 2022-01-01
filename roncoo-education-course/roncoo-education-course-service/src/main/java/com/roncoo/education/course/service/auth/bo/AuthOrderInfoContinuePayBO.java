package com.roncoo.education.course.service.auth.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 订单继续支付
 *
 * @author YZJ
 */
@Data
@Accessors(chain = true)
public class AuthOrderInfoContinuePayBO implements Serializable {

    private static final long serialVersionUID = 1L;
    /***
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号", required = true)
    private long orderNo;
    /***
     * 支付类型
     */
    @ApiModelProperty(value = "支付方式：1微信支付，2支付宝支付", required = true)
    private Integer payType;

}
