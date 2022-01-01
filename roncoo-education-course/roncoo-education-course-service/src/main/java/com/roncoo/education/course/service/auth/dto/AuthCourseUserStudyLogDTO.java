package com.roncoo.education.course.service.auth.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 课程用户学习日志
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AuthCourseUserStudyLogDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 课程编号
     */
    private Long courseNo;
    /**
     * 章节编号
     */
    private Long chapterNo;
    /**
     * 课时编号
     */
    private Long periodNo;
    /**
     * 用户编号
     */
    private Long userNo;
}
