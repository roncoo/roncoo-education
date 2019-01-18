package com.roncoo.education.web.boss.biz.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.roncoo.education.system.common.bean.qo.WebsiteQO;
import com.roncoo.education.system.common.bean.vo.WebsiteVO;
import com.roncoo.education.system.feign.IBossSys;
import com.roncoo.education.system.feign.IBossWebsite;
import com.roncoo.education.util.aliyun.Aliyun;
import com.roncoo.education.util.aliyun.AliyunUtil;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.enums.PlatformEnum;
import com.roncoo.education.util.tools.BeanUtil;

/**
 * 站点信息
 *
 * @author wuyun
 */
@Component
public class WebsiteBiz extends BaseBiz {

	@Autowired
	private IBossSys bossSys;
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
	 * @param logoImgFile
	 * @param logoIcoFile
	 * @param weixinFile
	 * @param weiboFile
	 * @param weixinXcxFile
	 * @return
	 * @author wuyun
	 */
	public int updateWebsite(WebsiteQO qo, MultipartFile weixinFile, MultipartFile weixinXcxFile, MultipartFile weiboFile, MultipartFile logoImgFile, MultipartFile logoIcoFile) {
		if (null != weixinFile && !weixinFile.isEmpty()) {
			qo.setWeixin(AliyunUtil.uploadPic(PlatformEnum.SYSTEM, weixinFile, BeanUtil.copyProperties(bossSys.getSys(), Aliyun.class)));
		}
		if (null != weixinXcxFile && !weixinXcxFile.isEmpty()) {
			qo.setWeixinXcx(AliyunUtil.uploadPic(PlatformEnum.SYSTEM, weixinXcxFile, BeanUtil.copyProperties(bossSys.getSys(), Aliyun.class)));
		}
		if (null != weiboFile && !weiboFile.isEmpty()) {
			qo.setWeibo(AliyunUtil.uploadPic(PlatformEnum.SYSTEM, weiboFile, BeanUtil.copyProperties(bossSys.getSys(), Aliyun.class)));
		}
		if (null != logoImgFile && !logoImgFile.isEmpty()) {
			qo.setLogoImg(AliyunUtil.uploadPic(PlatformEnum.SYSTEM, logoImgFile, BeanUtil.copyProperties(bossSys.getSys(), Aliyun.class)));
		}
		if (null != logoIcoFile && !logoIcoFile.isEmpty()) {
			qo.setLogoIco(AliyunUtil.uploadPic(PlatformEnum.SYSTEM, logoIcoFile, BeanUtil.copyProperties(bossSys.getSys(), Aliyun.class)));
		}
		return bossWebsite.updateById(qo);
	}

	/**
	 * 保存站点信息
	 * 
	 * @param qo
	 * @param logoImgFile
	 * @param logoIcoFile
	 * @param weixinFile
	 * @param weiboFile
	 * @param weixinXcxFile
	 * @return
	 * @author YZJ
	 */
	public int saveWebsite(WebsiteQO qo, MultipartFile weixinFile, MultipartFile weixinXcxFile, MultipartFile weiboFile, MultipartFile logoImgFile, MultipartFile logoIcoFile) {
		if (null != weixinFile && !weixinFile.isEmpty()) {
			qo.setWeixin(AliyunUtil.uploadPic(PlatformEnum.SYSTEM, weixinFile, BeanUtil.copyProperties(bossSys.getSys(), Aliyun.class)));
		}
		if (null != weixinXcxFile && !weixinXcxFile.isEmpty()) {
			qo.setWeixinXcx(AliyunUtil.uploadPic(PlatformEnum.SYSTEM, weixinXcxFile, BeanUtil.copyProperties(bossSys.getSys(), Aliyun.class)));
		}
		if (null != weiboFile && !weiboFile.isEmpty()) {
			qo.setWeibo(AliyunUtil.uploadPic(PlatformEnum.SYSTEM, weiboFile, BeanUtil.copyProperties(bossSys.getSys(), Aliyun.class)));
		}
		if (null != logoImgFile && !logoImgFile.isEmpty()) {
			qo.setLogoImg(AliyunUtil.uploadPic(PlatformEnum.SYSTEM, logoImgFile, BeanUtil.copyProperties(bossSys.getSys(), Aliyun.class)));
		}
		if (null != logoIcoFile && !logoIcoFile.isEmpty()) {
			qo.setLogoIco(AliyunUtil.uploadPic(PlatformEnum.SYSTEM, logoIcoFile, BeanUtil.copyProperties(bossSys.getSys(), Aliyun.class)));
		}
		return bossWebsite.save(qo);
	}

}
