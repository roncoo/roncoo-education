package com.roncoo.education.web.boss.biz.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.system.common.bean.qo.WebsiteRecruitQO;
import com.roncoo.education.system.common.bean.vo.WebsiteRecruitVO;
import com.roncoo.education.system.feign.web.IBossWebsiteRecruit;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Page;

/**
 * 招募中心 
 *
 * @author YZJ
 */
@Component
public class WebsiteRecruitBiz extends BaseBiz{

	@Autowired
	private IBossWebsiteRecruit bossWebsiteRecruit;

	public Page<WebsiteRecruitVO> listForPage(WebsiteRecruitQO qo) {
        return bossWebsiteRecruit.listForPage(qo);
	}

	public int save(WebsiteRecruitQO qo) {
		return bossWebsiteRecruit.save(qo);
	}

	public int deleteById(Long id) {
		return bossWebsiteRecruit.deleteById(id);
	}

	public WebsiteRecruitVO getById(Long id) {
		return bossWebsiteRecruit.getById(id);
	}

	public int updateById(WebsiteRecruitQO qo) {
		return bossWebsiteRecruit.updateById(qo);
	}
	
}
