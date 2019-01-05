package com.roncoo.education.system.common.bean.qo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

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
	
	
	//招募信息-代理招募
	/**
	 * 主键
	 */
	private Long agentId;
	/**
	 * 排序
	 */
	private Integer agentSort;
	/**
	 * 招募分类(1讲师招募,2代理招募)
	 */
	private Integer agentRecruitType;
	/**
	 * 招募标题
	 */
	private String agentRecruitTitle;
	/**
	 * 招募信息
	 */
	private String agentRecruitInfo;
	/**
	 * 入驻协议
	 */
	private String agentEntryAgreement;

	// 招募信息-讲师招募
	/**
	 * 主键
	 */
	private Long lecturerId;
	/**
	 * 排序
	 */
	private Integer lecturerSort;
	/**
	 * 招募分类(1讲师招募,2代理招募)
	 */
	private Integer lecturerRecruitType;
	/**
	 * 招募标题
	 */
	private String lecturerRecruitTitle;
	/**
	 * 招募信息
	 */
	private String lecturerRecruitInfo;
	/**
	 * 入驻协议
	 */
	private String lecturerEntryAgreement;
	
}
