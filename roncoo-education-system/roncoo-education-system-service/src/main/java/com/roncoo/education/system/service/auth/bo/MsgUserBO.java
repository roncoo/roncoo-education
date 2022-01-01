package com.roncoo.education.system.service.auth.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 站内信用户记录表
 *
 * @author LHR
 */
@Data
@Accessors(chain = true)
public class MsgUserBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号", required = true)
    private Long userNo;

    /**
     * 是否阅读(1是;0否)
     */
    @ApiModelProperty(value = "是否已阅读(1是;0否)")
    private Integer isRead;

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页")
    private Integer pageCurrent = 1;
    /**
     * 每页条数
     */
    @ApiModelProperty(value = "每页条数")
    private Integer pageSize = 20;
}
