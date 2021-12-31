package com.roncoo.education.system.service.auth.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 获得学员未读消息总条数
 *
 * @author kyh
 */
@Data
@Accessors(chain = true)
public class MsgReadBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号", required = true)
    private Long userNo;

}
