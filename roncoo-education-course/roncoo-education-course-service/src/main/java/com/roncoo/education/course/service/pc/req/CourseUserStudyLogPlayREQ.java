package com.roncoo.education.course.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 课程用户学习日志-分页列出
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class CourseUserStudyLogPlayREQ implements Serializable {

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
     * 课程ID
     */
    @ApiModelProperty(value = "课程ID", required = false)
    private Long courseId;
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
