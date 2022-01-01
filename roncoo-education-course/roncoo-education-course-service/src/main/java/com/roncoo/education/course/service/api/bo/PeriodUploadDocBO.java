package com.roncoo.education.course.service.api.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 课程信息
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class PeriodUploadDocBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 课时id
     */
    @ApiModelProperty(value = "课时id")
    private Long periodId;

}
