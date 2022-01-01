package com.roncoo.education.course.service.pc.resq;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 专区课程关联表-分页列出
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class ZoneCoursePageRESQ implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;
    /**
     * 状态(1:正常;0:禁用)
     */
    @ApiModelProperty(value = "状态(1:正常;0:禁用)")
    private Integer statusId;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;
    /**
     * 课程ID
     */
    @ApiModelProperty(value = "课程ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long courseId;
    /**
     * 课程名称
     */
    @ApiModelProperty(value = "课程名称")
    private String courseName;
    /**
     * 位置(0电脑端，1微信端)
     */
    @ApiModelProperty(value = "位置(0电脑端，1微信端)")
    private Integer zoneLocation;
    /**
     * 一级分类名
     */
    @ApiModelProperty(value = "一级分类名")
    private String categoryName1;
    /**
     * 二级分类名
     */
    @ApiModelProperty(value = "二级分类名")
    private String categoryName2;
    /**
     * 三级分类名
     */
    @ApiModelProperty(value = "三级分类名")
    private String categoryName3;
}
