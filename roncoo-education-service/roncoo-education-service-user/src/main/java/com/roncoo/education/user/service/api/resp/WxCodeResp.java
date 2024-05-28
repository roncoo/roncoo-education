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
public class WxCodeResp extends UsersLoginResp implements Serializable {

    private static final long serialVersionUID = 2621609267080102065L;

    @ApiModelProperty(value = "绑定状态(true已绑定，false没绑定)")
    private Boolean bindingStatus = false;

    @ApiModelProperty(value = "授权信息")
    private WxOAuth2UserInfo userInfo;

}
