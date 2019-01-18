package com.roncoo.education.system.common.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.system.common.bean.qo.WebsiteNavQO;
import com.roncoo.education.system.common.bean.vo.WebsiteNavVO;
import com.roncoo.education.util.base.Page;

/**
 * 站点导航
 *
 * @author wuyun
 */
public interface BossWebsiteNav {

	@RequestMapping(value = "/boss/system/websiteNav/listForPage", method = RequestMethod.POST)
	Page<WebsiteNavVO> listForPage(@RequestBody WebsiteNavQO qo);

	@RequestMapping(value = "/boss/system/websiteNav/save", method = RequestMethod.POST)
	int save(@RequestBody WebsiteNavQO qo);

	@RequestMapping(value = "/boss/system/websiteNav/deleteById", method = RequestMethod.DELETE)
	int deleteById(@RequestBody Long id);

	@RequestMapping(value = "/boss/system/websiteNav/updateById", method = RequestMethod.PUT)
	int updateById(@RequestBody WebsiteNavQO qo);

	@RequestMapping(value = "/boss/system/websiteNav/get/{id}", method = RequestMethod.GET)
	WebsiteNavVO getById(@PathVariable(value = "id") Long id);

}
