package com.roncoo.education.course.feign.interfaces.vo;

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
public class CourseUserStudyLogVO implements Serializable {

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
    private Long courseId;
    /**
     * 章节编号
     */
    private Long chapterId;
    /**
     * 课时编号
     */
    private Long periodId;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 章节名称
     */
    private String chapterName;
    /**
     * 课时名称
     */
    private String periodName;
    /**
     * 用户编号
     */
    private Long userNo;

}
