package com.roncoo.education.system.service.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.system.common.bean.qo.WebsiteRecruitQO;
import com.roncoo.education.system.common.bean.vo.WebsiteRecruitVO;
import com.roncoo.education.system.common.interfaces.web.BossWebsiteRecruit;
import com.roncoo.education.system.service.biz.web.BossWebsiteRecruitBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;

/**
 * 招募中心
 *
 * @author YZJ
 */
@RestController
public class BossWebsiteRecruitController extends BaseController implements BossWebsiteRecruit {

	@Autowired
	private BossWebsiteRecruitBiz biz;

	@Override
	public Page<WebsiteRecruitVO> listForPage(@RequestBody WebsiteRecruitQO qo) {
		return biz.listForPage(qo);
	}

	@Override
	public int save(@RequestBody WebsiteRecruitQO qo) {
		return biz.save(qo);
	}

	@Override
	public int deleteById(@RequestBody Long id) {
		return biz.deleteById(id);
	}

	@Override
	public int updateById(@RequestBody WebsiteRecruitQO qo) {
		return biz.updateById(qo);
	}

	@Override
	public WebsiteRecruitVO getById(@RequestBody Long id) {
		return biz.getById(id);
	}

	@Override
	public List<WebsiteRecruitVO> listWebsiteRecruit() {
		return biz.listWebsiteRecruit();
	}

}
