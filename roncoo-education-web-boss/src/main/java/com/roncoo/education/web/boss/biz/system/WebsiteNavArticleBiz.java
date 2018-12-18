package com.roncoo.education.web.boss.biz.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.roncoo.education.util.aliyun.AliyunOssUtil;
import com.roncoo.education.util.enums.PlatformEnum;
import com.roncoo.education.system.common.bean.qo.WebsiteNavArticleQO;
import com.roncoo.education.system.common.bean.vo.WebsiteNavArticleVO;
import com.roncoo.education.system.feign.web.IBossWebsiteNavArticle;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Page;

/**
 * 站点导航文章
 *
 * @author wuyun
 */
@Component
public class WebsiteNavArticleBiz extends BaseBiz {

	@Autowired
	private IBossWebsiteNavArticle bossWebsiteNavArticle;

	public Page<WebsiteNavArticleVO> listForPage(WebsiteNavArticleQO qo) {
		return bossWebsiteNavArticle.listForPage(qo);
	}

	public int save(WebsiteNavArticleQO qo, MultipartFile articleFile) {
		if (articleFile != null && !articleFile.isEmpty()) {
			qo.setArtPic(AliyunOssUtil.uploadPic(PlatformEnum.SYSTEM, articleFile));
		}
		return bossWebsiteNavArticle.save(qo);
	}

	public int deleteById(Long id) {
		return bossWebsiteNavArticle.deleteById(id);
	}

	public WebsiteNavArticleVO getById(Long id) {
		return bossWebsiteNavArticle.getById(id);
	}

	public int updateById(WebsiteNavArticleQO qo, MultipartFile articleFile) {
		if (articleFile != null && !articleFile.isEmpty()) {
			qo.setArtPic(AliyunOssUtil.uploadPic(PlatformEnum.SYSTEM, articleFile));
		}
		return bossWebsiteNavArticle.updateById(qo);
	}

	public WebsiteNavArticleVO getByNavId(Long navId) {
		return bossWebsiteNavArticle.getByNavId(navId);
	}

}
