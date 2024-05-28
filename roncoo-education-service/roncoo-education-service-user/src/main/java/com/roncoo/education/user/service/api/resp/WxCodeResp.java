package com.roncoo.education.user.service.api.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;

import java.io.Serializable;

/**
 * @author fengyw
 */
@Data
@Accessors(chain = true)
public class WxCodeResp implements Serializable {

    private static final long serialVersionUID = 2621609267080102065L;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "token，有效期为1天")
    private String token;

    @ApiModelProperty(value = "绑定状态(true已绑定，false没绑定)")
    private Boolean bindingStatus = false;

    @ApiModelProperty(value = "授权信息")
    private WxOAuth2UserInfo userInfo;

}
