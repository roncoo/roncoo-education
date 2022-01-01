package com.roncoo.education.user.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 讲师信息-审核
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class LecturerAuditAuditREQ implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID", required = true)
    private Long id;
    /**
     * 审核状态(0:待审核,1:审核通过,2:审核不通过)
     */
    @ApiModelProperty(value = "审核状态(0:待审核,1:审核通过,2:审核不通过)", required = true)
    private Integer auditStatus;
    /**
     * 审核意见
     */
    @ApiModelProperty(value = "审核意见", required = false)
    private String auditOpinion;

}
