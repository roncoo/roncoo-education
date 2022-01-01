package com.roncoo.education.course.service.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 课程视频信息
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class CourseVideoDTO implements Serializable {

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
     * 状态(1:正常，0:禁用)
     */
    private Integer statusId;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 讲师用户编号
     */
    private Long lecturerUserNo;
    /**
     * 课程ID
     */
    private Long courseId;
    /**
     * 章节ID
     */
    private Long chapterId;
    /**
     * 课时ID
     */
    private Long periodId;
    /**
     * 视频名称
     */
    private String videoName;
    /**
     * 视频状态(1待上传，2上传成功，3上传失败)
     */
    private Integer videoStatus;
    /**
     * 时长
     */
    private String videoLength;
    /**
     * 视频ID
     */
    private String videoVid;
    /**
     * 阿里云oas
     */
    private String videoOasId;
}
