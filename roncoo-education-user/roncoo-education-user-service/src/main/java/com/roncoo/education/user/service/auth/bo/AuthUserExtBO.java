package com.roncoo.education.user.service.auth.bo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用户教育信息
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AuthUserExtBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "主键", required = true)
    private Long id;
    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号", required = true)
    private Long userNo;
    /**
     * 性别(1男，2女，3保密)
     */
    @ApiModelProperty(value = "性别(1男，2女，3保密)", required = false)
    private Integer sex;
    /**
     * 年龄
     */
    @ApiModelProperty(value = "年龄", required = false)
    private Integer age;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称", required = false)
    private String nickname;
    /**
     * 头像地址
     */
    @ApiModelProperty(value = "头像地址", required = false)
    private String headImgUrl;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", required = false)
    private String remark;
}
