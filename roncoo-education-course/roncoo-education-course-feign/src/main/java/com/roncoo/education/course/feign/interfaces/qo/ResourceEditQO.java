package com.roncoo.education.course.feign.interfaces.qo;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程视频信息
 *
 * @author wujing
 * @date 2022-08-27
 */
@Data
@Accessors(chain = true)
public class ResourceEditQO implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;

    /**
     * 状态(1:正常，0:禁用)
     */
    private Integer statusId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 视频名称
     */
    private String recourseName;

    /**
     * 文件大小
     */
    private Long recourseSize;

    /**
     * 资源类型(1:视频;2:文件)
     */
    private Integer recourseType;

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
     * 文件地址
     */
    private String fileUrl;

    /**
     * 文件名称
     */
    private String fileName;

}
