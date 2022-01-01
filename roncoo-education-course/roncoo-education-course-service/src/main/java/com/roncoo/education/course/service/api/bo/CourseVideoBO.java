package com.roncoo.education.course.service.api.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 课程视频信息
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class CourseVideoBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 课程ID
     */
    @ApiModelProperty(value = "课程ID")
    private Long courseId;

}
