package com.roncoo.education.course.service.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 课程介绍信息
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class CourseIntroduceBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 课程简介
     */
    private String introduce;
}
