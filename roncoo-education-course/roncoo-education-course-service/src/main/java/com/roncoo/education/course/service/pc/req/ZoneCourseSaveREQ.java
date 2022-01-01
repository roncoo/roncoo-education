package com.roncoo.education.course.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 专区课程-添加
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class ZoneCourseSaveREQ implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 专区ID
     */
    @ApiModelProperty(value = "专区ID", required = true)
    private Long zoneId;
    /**
     * 课程ID
     */
    @ApiModelProperty(value = "课程ID", required = true)
    private Long courseId;
}
