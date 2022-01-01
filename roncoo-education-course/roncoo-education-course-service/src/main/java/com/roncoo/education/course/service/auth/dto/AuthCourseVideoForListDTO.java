package com.roncoo.education.course.service.auth.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 课程视频信息
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class AuthCourseVideoForListDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 视频名称
     */
    @ApiModelProperty(value = "视频名称")
    private String videoName;
    /**
     * 视频时长
     */
    @ApiModelProperty(value = "视频时长")
    private String videoLength;
    /**
     * 视频vid
     */
    @ApiModelProperty(value = "视频vid")
    private String videoVid;
    /**
     * 视频状态
     */
    @ApiModelProperty(value = "视频状态(1待上传，2上传成功，3上传失败)")
    private Integer videoStatus;
    /**
     * 视频编号
     */
    @ApiModelProperty(value = "视频编号", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long videoNo;
}
