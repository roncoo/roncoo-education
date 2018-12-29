package com.roncoo.education.system.common.bean.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 站点信息
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class WebsiteDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键id")
	@JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;
    /**
     * 状态(1有效, 0无效)
     */
    @ApiModelProperty(value = "状态(1有效, 0无效)")
    private Integer statusId;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;
    /**
     * 站点标题
     */
    @ApiModelProperty(value = "站点标题")
    private String websiteTitle;
    /**
     * 站点关键词
     */
    @ApiModelProperty(value = "站点关键词")
    private String websiteKeyword;
    /**
     * 站点描述
     */
    @ApiModelProperty(value = "站点描述")
    private String websiteDesc;
    /**
     * 站点版权
     */
    @ApiModelProperty(value = "站点版权")
    private String copyright;
    /**
     * 站点微信
     */
    @ApiModelProperty(value = "站点微信")
    private String weixin;
    /**
     * 站点微博
     */
    @ApiModelProperty(value = "站点微博")
    private String weibo;
    /**
     * 是否开启统计
     */
    @ApiModelProperty(value = "是否开启统计")
    private Integer isEnableStatistics;
    /**
     * 统计代码
     */
    @ApiModelProperty(value = "统计代码")
    private String statisticsCode;
    /**
     * 是否显示客服信息
     */
    @ApiModelProperty(value = "是否显示客服信息")
    private Integer isShowService;
    /**
     * 客服信息1
     */
    @ApiModelProperty(value = "客服信息1")
    private String service1;
    /**
     * 客服信息2
     */
    @ApiModelProperty(value = "客服信息2")
    private String service2;
    /**
     * logoImg
     */
    @ApiModelProperty(value = "logoImg")
    private String logoImg;
    /**
     * logoIco
     */
    @ApiModelProperty(value = "logoIco")
    private String logoIco;
    /**
	 * 用户协议
	 */
    @ApiModelProperty(value = "用户协议")
	private String userAgreement;
}
