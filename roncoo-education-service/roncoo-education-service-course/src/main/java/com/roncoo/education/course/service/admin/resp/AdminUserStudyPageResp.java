package com.roncoo.education.course.service.admin.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * ADMIN-资源学习记录
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "ADMIN-资源学习记录分页")
public class AdminUserStudyPageResp implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "章节ID")
    private Long id;

    @ApiModelProperty(value = "课程ID")
    private Long courseId;

    @ApiModelProperty(value = "章节名称")
    private String chapterName;

    private List<AdminUserStudyPeriodPageResp> userStudyPeriodPageRespList;
}
