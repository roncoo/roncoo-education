package com.roncoo.education.course.service.api.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 课程推荐
 *
 * @author kyh
 */
@Data
@Accessors(chain = true)
public class CourseRecommendBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分类ID
     */
    @ApiModelProperty(value = "分类ID", required = true)
    private Long categoryId;

}
