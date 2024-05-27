package com.roncoo.education.user.service.auth.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * API-AUTH-订单支付信息表
 * </p>
 *
 * @author wujing
 * @date 2022-08-25
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "API-AUTH-订单支付信息表")
public class AuthOrderPayReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程ID")
    private Long courseId;

    @ApiModelProperty(value = "支付方式，参考枚举PayTypeEnum")
    private Integer payType;

    @ApiModelProperty(value = "用户终端IP")
    private String userClientIp;

    @ApiModelProperty(value = "用户付款中途退出的回调地址")
    private String quitUrl;

    @ApiModelProperty(value = "用户备注", required = false)
    private String remarkCus;

    @ApiModelProperty(value = "微信OpenId(微信公众号支付、微信小程序支付使用)", required = false)
    private String openId;

    @ApiModelProperty(value = "授权Code(openId和code不能同时为空，优先使用openId)", required = false)
    private String code;

}
