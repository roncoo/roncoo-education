package com.roncoo.education.system.service.auth.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 站内信息表
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class MsgReadDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户站内信未读条数
     */
    @ApiModelProperty(value = "用户站内信未读条数")
    private Integer num;
}
