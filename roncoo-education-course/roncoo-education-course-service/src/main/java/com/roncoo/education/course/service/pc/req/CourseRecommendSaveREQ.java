package com.roncoo.education.course.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 课程推荐-添加
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class CourseRecommendSaveREQ implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 分类ID
     */
    @ApiModelProperty(value = "分类ID", required = true)
    private Long categoryId;
    /**
     * 课程ID
     */
    @ApiModelProperty(value = "课程ID", required = true)
    private Long courseId;

}
