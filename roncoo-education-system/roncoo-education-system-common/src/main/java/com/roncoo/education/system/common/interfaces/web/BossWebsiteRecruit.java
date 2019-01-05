package com.roncoo.education.system.common.interfaces.web;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.roncoo.education.system.common.bean.qo.WebsiteRecruitQO;
import com.roncoo.education.system.common.bean.vo.WebsiteRecruitVO;
import com.roncoo.education.util.base.Page;

/**
 * 招募中心
 *
 * @author YZJ
 */
public interface BossWebsiteRecruit {

	@RequestMapping(value = "/boss/system/websiteRecruit/listForPage")
	Page<WebsiteRecruitVO> listForPage(@RequestBody WebsiteRecruitQO qo);

	@RequestMapping(value = "/boss/system/websiteRecruit/save")
	int save(@RequestBody WebsiteRecruitQO qo);

	@RequestMapping(value = "/boss/system/websiteRecruit/deleteById")
	int deleteById(@RequestBody Long id);

	@RequestMapping(value = "/boss/system/websiteRecruit/updateById")
	int updateById(@RequestBody WebsiteRecruitQO qo);

	@RequestMapping(value = "/boss/system/websiteRecruit/getById")
	WebsiteRecruitVO getById(@RequestBody Long id);

	/**
	 * 获取招募集合信息
	 * 
	 * @return
	 * @author YZJ
	 */
	@RequestMapping(value = "/boss/system/websiteRecruit/listWebsiteRecruit")
	List<WebsiteRecruitVO> listWebsiteRecruit();

}
