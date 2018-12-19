package com.roncoo.education.web.boss.biz.system;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.roncoo.education.system.common.bean.qo.WebsiteQO;
import com.roncoo.education.system.common.bean.vo.WebsiteVO;
import com.roncoo.education.system.feign.web.IBossWebsite;
import com.roncoo.education.util.aliyun.AliyunOssUtil;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.enums.PlatformEnum;

/**
 * 站点信息
 *
 * @author wuyun
 */
@Component
public class WebsiteBiz extends BaseBiz {

	@Autowired
	private IBossWebsite bossWebsite;

	public Page<WebsiteVO> listForPage(WebsiteQO qo) {
		return bossWebsite.listForPage(qo);
	}

	public int save(WebsiteQO qo) {
		return bossWebsite.save(qo);
	}

	public int deleteById(Long id) {
		return bossWebsite.deleteById(id);
	}

	public WebsiteVO getById(Long id) {
		return bossWebsite.getById(id);
	}

	public int updateById(WebsiteQO qo) {
		return bossWebsite.updateById(qo);
	}

	public WebsiteVO getWebsite() {
		return bossWebsite.getWebsite();
	}

	/**
	 * 修改站点信息
	 * 
	 * @param qo
	 * @param polyvLogoFile
	 * @param picWatermarkFile
	 * @param weixinFile
	 * @param weiboFile
	 * @return
	 * @author wuyun
	 */
	public int updateWebsite(WebsiteQO qo, MultipartFile polyvLogoFile, MultipartFile picWatermarkFile, MultipartFile weixinFile, MultipartFile weiboFile,MultipartFile logoImgFile,MultipartFile logoIcoFile) {
		if (StringUtils.isNotBlank(qo.getPicxy())) {
			String[] picxy = qo.getPicxy().split(",");
			List<String> list = Arrays.asList(picxy);
			if (!list.get(0).equals("")) {
				qo.setPicx(Integer.parseInt(list.get(0)));
			}
			if (!list.get(1).equals("")) {
				qo.setPicy(Integer.parseInt(list.get(1)));
			}
		}
		if (null != polyvLogoFile && !polyvLogoFile.isEmpty()) {
			qo.setPolyvLogo(AliyunOssUtil.uploadPic(PlatformEnum.SYSTEM, polyvLogoFile));
		}
		if (null != picWatermarkFile && !picWatermarkFile.isEmpty()) {
			qo.setPicWatermark(AliyunOssUtil.uploadPic(PlatformEnum.SYSTEM, picWatermarkFile));
		}
		if (null != weixinFile && !weixinFile.isEmpty()) {
			qo.setWeixin(AliyunOssUtil.uploadPic(PlatformEnum.SYSTEM, weixinFile));
		}
		if (null != weiboFile && !weiboFile.isEmpty()) {
			qo.setWeibo(AliyunOssUtil.uploadPic(PlatformEnum.SYSTEM, weiboFile));
		}
		if (null != logoImgFile && !logoImgFile.isEmpty()) {
			qo.setLogoImg(AliyunOssUtil.uploadPic(PlatformEnum.SYSTEM, logoImgFile));
		}
		if (null != logoIcoFile && !logoIcoFile.isEmpty()) {
			qo.setLogoIco(AliyunOssUtil.uploadPic(PlatformEnum.SYSTEM, logoIcoFile));
		}
		return bossWebsite.updateById(qo);
	}

}
