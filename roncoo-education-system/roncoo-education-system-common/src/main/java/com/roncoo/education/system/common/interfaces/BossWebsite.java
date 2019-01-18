package com.roncoo.education.system.common.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.system.common.bean.qo.WebsiteQO;
import com.roncoo.education.system.common.bean.vo.WebsiteVO;
import com.roncoo.education.util.base.Page;

/**
 * 站点信息
 *
 * @author wuyun
 */
public interface BossWebsite {

	@RequestMapping(value = "/boss/system/website/listForPage", method = RequestMethod.POST)
	Page<WebsiteVO> listForPage(@RequestBody WebsiteQO qo);

	@RequestMapping(value = "/boss/system/website/save", method = RequestMethod.POST)
	int save(@RequestBody WebsiteQO qo);

	@RequestMapping(value = "/boss/system/website/deleteById", method = RequestMethod.DELETE)
	int deleteById(@RequestBody Long id);

	@RequestMapping(value = "/boss/system/website/updateById", method = RequestMethod.PUT)
	int updateById(@RequestBody WebsiteQO qo);

	@RequestMapping(value = "/boss/system/website/get/{id}", method = RequestMethod.GET)
	WebsiteVO getById(@PathVariable(value = "id") Long id);

	/**
	 * 获取站点信息
	 *
	 * @author wuyun
	 */
	@RequestMapping(value = "/boss/system/website/getWebsite", method = RequestMethod.GET)
	WebsiteVO getWebsite();
}
