package com.roncoo.education.system.common.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.system.common.bean.qo.WebsiteLinkQO;
import com.roncoo.education.system.common.bean.vo.WebsiteLinkVO;
import com.roncoo.education.util.base.Page;

/**
 * 站点友情链接
 *
 * @author wuyun
 */
public interface BossWebsiteLink {

	@RequestMapping(value = "/boss/system/websiteLink/listForPage", method = RequestMethod.POST)
	Page<WebsiteLinkVO> listForPage(@RequestBody WebsiteLinkQO qo);

	@RequestMapping(value = "/boss/system/websiteLink/save", method = RequestMethod.POST)
	int save(@RequestBody WebsiteLinkQO qo);

	@RequestMapping(value = "/boss/system/websiteLink/deleteById", method = RequestMethod.DELETE)
	int deleteById(@RequestBody Long id);

	@RequestMapping(value = "/boss/system/websiteLink/updateById", method = RequestMethod.PUT)
	int updateById(@RequestBody WebsiteLinkQO qo);

	@RequestMapping(value = "/boss/system/websiteLink/get/{id}", method = RequestMethod.GET)
	WebsiteLinkVO getById(@PathVariable(value = "id") Long id);

}
