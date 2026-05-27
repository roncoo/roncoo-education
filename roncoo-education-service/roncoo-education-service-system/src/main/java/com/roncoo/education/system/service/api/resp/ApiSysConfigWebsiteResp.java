package com.roncoo.education.system.service.api.resp;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "API-AUTH-系统配置")
public class ApiSysConfigWebsiteResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "网站域名")
    private String websiteDomain;

    @Schema(description = "网站名称")
    private String websiteName;

    @Schema(description = "网站描述")
    private String websiteDesc;

    @Schema(description = "网站LOGO")
    private String websiteLogo;

    @Schema(description = "网站ICON")
    private String websiteIcon;

    @Schema(description = "网站ICP")
    private String websiteIcp;

    @Schema(description = "公安部备案号")
    private String websitePrn;

    @Schema(description = "公安部备案号的编号")
    private String websitePrnNo;

    @Schema(description = "网站版权")
    private String websiteCopyright;

    @Schema(description = "用户协议")
    private String websiteUserAgreement;

    @Schema(description = "隐私政策")
    private String websitePrivacyPolicy;

    @Schema(description = "关于我们")
    private String websiteAboutUs;

    @Schema(description = "登录加密公钥")
    private String rsaLoginPublicKey;

    @Schema(description = "是否开启微信网页应用授权")
    private String wxPcLoginEnable;

    @Schema(description = "是否开启微信公众号授权")
    private String wxMpLoginEnable;

    @Schema(description = "是否开启微信小程序授权")
    private String wxMaLoginEnable;

}
