package com.roncoo.education.course.service.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 专区课程关联表
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class ZoneCourseBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 专区编号
     */
    private Long zoneId;
}
