package com.roncoo.education.course.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 广告信息-添加
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AdvSaveREQ implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 位置(0电脑端，1微信端)
     */
    @ApiModelProperty(value = "位置(0电脑端，1微信端)", required = false)
    private Integer platShow;
    /**
     * 广告标题
     */
    @ApiModelProperty(value = "广告标题", required = true)
    private String advTitle;
    /**
     * 广告图片
     */
    @ApiModelProperty(value = "广告图片", required = true)
    private String advImg;
    /**
     * 广告链接
     */
    @ApiModelProperty(value = "广告链接", required = true)
    private String advUrl;
    /**
     * 跳转方式（_blank：新窗口打开；_self：同窗口打开）
     */
    @ApiModelProperty(value = "跳转方式（_blank：新窗口打开；_self：同窗口打开）", required = true)
    private String advTarget;
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间", required = true)
    private String beginTime;
    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间", required = true)
    private String endTime;

}
