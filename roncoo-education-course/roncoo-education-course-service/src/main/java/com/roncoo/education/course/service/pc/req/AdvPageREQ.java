package com.roncoo.education.course.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 广告信息-分页列出
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AdvPageREQ implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页", required = true)
    private int pageCurrent = 1;
    /**
     * 每页记录数
     */
    @ApiModelProperty(value = "每页记录数", required = true)
    private int pageSize = 20;
    /**
     * 状态(1:正常，0:禁用)
     */
    @ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
    private Integer statusId;
    /**
     * 广告标题
     */
    @ApiModelProperty(value = "广告标题", required = false)
    private String advTitle;
    /**
     * 位置(0电脑端，1微信端)
     */
    @ApiModelProperty(value = "位置(0电脑端，1微信端)", required = false)
    private Integer platShow;

}
