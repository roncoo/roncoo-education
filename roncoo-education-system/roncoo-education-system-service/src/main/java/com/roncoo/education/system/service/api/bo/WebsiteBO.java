package com.roncoo.education.system.service.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 站点信息
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class WebsiteBO implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 状态(1有效, 0无效)
     */
    private Integer statusId;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 站点标题
     */
    private String websiteTitle;
    /**
     * 站点关键词
     */
    private String websiteKeyword;
    /**
     * 站点描述
     */
    private String websiteDesc;
    /**
     * 站点版权
     */
    private String copyright;
    /**
     * 站点微信
     */
    private String weixin;
    /**
     * 站点微博
     */
    private String weibo;
    /**
     * 是否开启统计
     */
    private Integer isEnableStatistics;
    /**
     * 统计代码
     */
    private String statisticsCode;
    /**
     * 是否显示客服信息
     */
    private Integer isShowService;
    /**
     * 客服信息1
     */
    private String service1;
    /**
     * 客服信息2
     */
    private String service2;
    /**
     * 客服信息3
     */
    private String service3;
    /**
     * 客服信息4
     */
    private String service4;
    /**
     * 客服信息5
     */
    private String service5;
}
