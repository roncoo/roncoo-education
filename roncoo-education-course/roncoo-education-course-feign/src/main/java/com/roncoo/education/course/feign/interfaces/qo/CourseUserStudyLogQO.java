package com.roncoo.education.course.feign.interfaces.qo;

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
public class CourseUserStudyLogQO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    private int pageCurrent;
    /**
     * 每页记录数
     */
    private int pageSize;
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
     * 用户编号
     */
    private Long userNo;

    /**
     * 开始时间
     */
    private String beginGmtCreate;
    /**
     * 结束时间
     */
    private String endGmtCreate;
}
