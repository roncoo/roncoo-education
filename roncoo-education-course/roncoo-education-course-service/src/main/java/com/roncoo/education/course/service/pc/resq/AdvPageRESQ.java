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
 * 广告信息- 分页列出
 */
@Data
@Accessors(chain = true)
public class AdvPageRESQ implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 状态(1:正常，0:禁用)
     */
    @ApiModelProperty(value = "状态(1:正常，0:禁用)")
    private Integer statusId;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;
    /**
     * 广告标题
     */
    @ApiModelProperty(value = "广告标题")
    private String advTitle;
    /**
     * 广告图片
     */
    @ApiModelProperty(value = "广告图片")
    private String advImg;
    /**
     * 广告链接
     */
    @ApiModelProperty(value = "广告链接")
    private String advUrl;
    /**
     * 跳转方式（_blank：新窗口打开；_self：同窗口打开）
     */
    @ApiModelProperty(value = "跳转方式（_blank：新窗口打开；_self：同窗口打开）")
    private String advTarget;
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;
    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

}
