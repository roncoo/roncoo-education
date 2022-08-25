package com.roncoo.education.system.feign.interfaces.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 广告信息
 *
 * @author wujing
 * @date 2022-08-25
 */
@Data
@Accessors(chain = true)
public class WebsiteAdvPageVO implements Serializable {

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
     * 广告标题
     */
    private String advTitle;

    /**
     * 广告图片
     */
    private String advImg;

    /**
     * 广告链接
     */
    private String advUrl;

    /**
     * 广告跳转方式
     */
    private String advTarget;

    /**
     * 广告位置(1首页轮播)
     */
    private Integer advLocation;

    /**
     * 开始时间
     */
    private LocalDateTime beginTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 位置(0电脑端，1微信端)
     */
    private Integer platShow;
}
