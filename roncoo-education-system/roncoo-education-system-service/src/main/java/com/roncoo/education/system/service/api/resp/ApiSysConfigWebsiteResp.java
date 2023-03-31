package com.roncoo.education.system.service.api.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * API-AUTH-系统配置
 * </p>
 *
 * @author wujing
 * @date 2022-08-25
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "API-AUTH-系统配置")
public class ApiSysConfigWebsiteResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "网站域名")
    private String websiteDomain;
    @ApiModelProperty(value = "网站名称")
    private String websiteName;
    @ApiModelProperty(value = "网站描述")
    private String websiteDesc;
    @ApiModelProperty(value = "网站LOGO")
    private String websiteLogo;
    @ApiModelProperty(value = "网站ICON")
    private String websiteIcon;
    @ApiModelProperty(value = "网站ICP")
    private String websiteIcp;
    @ApiModelProperty(value = "公安部备案号")
    private String websitePrn;
    @ApiModelProperty(value = "公安部备案号的编号")
    private String websitePrnNo;
    @ApiModelProperty(value = "网站版权")
    private String websiteCopyright;
    @ApiModelProperty(value = "用户协议")
    private String websiteUserAgreement;

}
