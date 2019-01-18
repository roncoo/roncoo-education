package com.roncoo.education.web.boss.biz.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.system.common.bean.qo.WebsiteLinkQO;
import com.roncoo.education.system.common.bean.vo.WebsiteLinkVO;
import com.roncoo.education.system.feign.IBossWebsiteLink;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Page;

/**
 * 站点友情链接 
 *
 * @author wuyun
 */
@Component
public class WebsiteLinkBiz extends BaseBiz{

	@Autowired
	private IBossWebsiteLink bossWebsiteLink;

	public Page<WebsiteLinkVO> listForPage(WebsiteLinkQO qo) {
        return bossWebsiteLink.listForPage(qo);
	}

	public int save(WebsiteLinkQO qo) {
		return bossWebsiteLink.save(qo);
	}

	public int deleteById(Long id) {
		return bossWebsiteLink.deleteById(id);
	}

	public WebsiteLinkVO getById(Long id) {
		return bossWebsiteLink.getById(id);
	}

	public int updateById(WebsiteLinkQO qo) {
		return bossWebsiteLink.updateById(qo);
	}

	public int updateStatusId(WebsiteLinkQO qo) {
		return bossWebsiteLink.updateById(qo);
	}
	
}
