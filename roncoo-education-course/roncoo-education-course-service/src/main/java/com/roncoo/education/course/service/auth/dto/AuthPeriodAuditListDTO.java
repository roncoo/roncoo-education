package com.roncoo.education.course.service.auth.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 课时信息-审核
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AuthPeriodAuditListDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 课时集合信息
     */
    @ApiModelProperty(value = "课时集合信息")
    private List<AuthPeriodAuditDTO> userPeriodAuditList = new ArrayList<>();

    /**
     * 视频数量
     */
    @ApiModelProperty(value = "视频数量")
    private Integer videoNum;
    /**
     * 文档数量
     */
    @ApiModelProperty(value = "文档数量")
    private Integer docNum;
}
