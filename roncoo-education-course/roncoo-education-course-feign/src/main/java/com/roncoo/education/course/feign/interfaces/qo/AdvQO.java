package com.roncoo.education.course.feign.interfaces.qo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 广告信息
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AdvQO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    private int pageCurrent;
    /**
     * 每页记录数
     */
    private int pageSize;
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
    private Date beginTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 位置(0电脑端，1微信端)
     */
    private Integer platShow;

    private String beginTimeString;
    private String endTimeString;
}
