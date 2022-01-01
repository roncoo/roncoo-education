package com.roncoo.education.course.feign.interfaces.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 课程介绍信息
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class CourseIntroduceVO implements Serializable {

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
     * 修改时间
     */
    private Date gmtModified;
    /**
     * 课程简介
     */
    private String introduce;

}
