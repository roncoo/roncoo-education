package com.roncoo.education.course.service.auth.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 课程信息-审核
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AuthCourseUserStudyLogPageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;
    /**
     * 课时编号
     */
    @ApiModelProperty(value = "课时编号")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long periodId;
    /**
     * 课时名称
     */
    @ApiModelProperty(value = "课时名称")
    private String periodName;
    /**
     * 课程编号
     */
    @ApiModelProperty(value = "课程编号")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long courseId;
    /**
     * 课程名称
     */
    @ApiModelProperty(value = "课程名称")
    private String courseName;
}
