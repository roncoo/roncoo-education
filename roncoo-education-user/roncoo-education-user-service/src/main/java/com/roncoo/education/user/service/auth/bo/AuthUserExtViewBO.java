package com.roncoo.education.user.service.auth.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用户教育信息
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class AuthUserExtViewBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号")
    private Long userNo;
}
