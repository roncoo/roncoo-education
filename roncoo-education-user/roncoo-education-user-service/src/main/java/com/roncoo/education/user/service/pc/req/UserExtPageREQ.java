package com.roncoo.education.user.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class UserExtPageREQ implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页", required = true)
    private int pageCurrent = 1;
    /**
     * 每页记录数
     */
    @ApiModelProperty(value = "每页记录数", required = true)
    private int pageSize = 20;
    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号", required = false)
    private Long userNo;
    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码", required = false)
    private String mobile;
    /**
     * 状态(1:正常，0:禁用)
     */
    @ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
    private Integer statusId;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称", required = false)
    private String nickname;
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间", required = false)
    private String beginGmtCreate;
    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间", required = false)
    private String endGmtCreate;

}
