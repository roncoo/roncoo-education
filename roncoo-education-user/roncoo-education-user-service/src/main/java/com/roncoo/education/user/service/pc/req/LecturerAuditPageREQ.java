package com.roncoo.education.user.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 讲师信息分页列出
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class LecturerAuditPageREQ implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态(1:正常，0:禁用)
     */
    @ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
    private Integer statusId;
    /**
     * 讲师名称
     */
    @ApiModelProperty(value = "讲师名称", required = false)
    private String lecturerName;
    /**
     * 讲师手机
     */
    @ApiModelProperty(value = "讲师手机", required = false)
    private String lecturerMobile;
    /**
     * 审核状态(0:待审核,1:审核通过,2:审核不通过)
     */
    @ApiModelProperty(value = "审核状态(0:待审核,1:审核通过,2:审核不通过)", required = false)
    private Integer auditStatus;
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

}
