package com.roncoo.education.system.service.api.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 验证码
 *
 * @author Admin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "验证码")
public class ApiVerCodeResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "verToken")
    private String verToken;

    @ApiModelProperty(value = "验证码图片")
    private String img;
}
