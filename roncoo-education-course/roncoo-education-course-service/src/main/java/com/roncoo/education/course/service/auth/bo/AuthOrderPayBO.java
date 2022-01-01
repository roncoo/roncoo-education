package com.roncoo.education.course.service.auth.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 订单支付信息表
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AuthOrderPayBO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户编号", required = true)
    private Long userNo;

    @ApiModelProperty(value = "课程编号", required = true)
    private Long courseId;

    @ApiModelProperty(value = "支付方式：1微信支付，2支付宝支付", required = true)
    private Integer payType;

    @ApiModelProperty(value = "购买渠道：1web", required = true)
    private Integer channelType;

    @ApiModelProperty(value = "用户备注", required = false)
    private String remarkCus;

}
