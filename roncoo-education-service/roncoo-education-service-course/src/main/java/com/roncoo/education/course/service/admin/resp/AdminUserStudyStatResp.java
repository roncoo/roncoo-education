package com.roncoo.education.course.service.admin.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * ADMIN-用户信息
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "ADMIN-用户信息统计")
public class AdminUserStudyStatResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "学习课程总数量")
    private Integer courseStudySum;

    @ApiModelProperty(value = "学习课程总时长")
    private Integer courseStudyDuration;

}
