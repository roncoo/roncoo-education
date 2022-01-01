package com.roncoo.education.course.service.pc.resq;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 章节信息-审核-查看
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class CourseChapterAuditViewRESQ implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "章节ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 课程ID
     */
    @ApiModelProperty(value = "课程ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long courseId;
    /**
     * 章节名称
     */
    @ApiModelProperty(value = "章节名称")
    private String chapterName;
    /**
     * 课时集合
     */
    @ApiModelProperty(value = "课时集合")
    private List<CourseChapterPeriodAuditViewRESQ> courseChapterPeriodAuditList = new ArrayList<>();
    ;
}
