package com.roncoo.education.system.feign.interfaces.qo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 设置信息集合，有站点信息，讲师招募信息，代理招募信息
 *
 * @author YZJ
 */
@Data
@Accessors(chain = true)
public class WebsiteQO implements Serializable {

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
     * 状态(1有效, 0无效)
     */
    private Integer statusId;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * logoIco
     */
    private String logoIco;
    /**
     * logoImg
     */
    private String logoImg;
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
     * 备案号
     */
    private String icp;
    /**
     * 公安备案号
     */
    private String prn;
    /**
     * 站点微信
     */
    private String weixin;
    /**
     * 小程序二维码
     */
    private String weixinXcx;
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
     * 用户协议
     */
    private String userAgreement;
    /**
     * 招募标题
     */
    private String recruitTitle;
    /**
     * 招募信息
     */
    private String recruitInfo;
    /**
     * 入驻协议
     */
    private String entryAgreement;

}
