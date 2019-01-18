package com.roncoo.education.system.common.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.system.common.bean.qo.WebsiteNavArticleQO;
import com.roncoo.education.system.common.bean.vo.WebsiteNavArticleVO;
import com.roncoo.education.util.base.Page;

/**
 * 站点导航文章
 *
 * @author wuyun
 */
public interface BossWebsiteNavArticle {

	@RequestMapping(value = "/boss/system/websiteNavArticle/listForPage", method = RequestMethod.POST)
	Page<WebsiteNavArticleVO> listForPage(@RequestBody WebsiteNavArticleQO qo);

	@RequestMapping(value = "/boss/system/websiteNavArticle/save", method = RequestMethod.POST)
	int save(@RequestBody WebsiteNavArticleQO qo);

	@RequestMapping(value = "/boss/system/websiteNavArticle/deleteById", method = RequestMethod.DELETE)
	int deleteById(@RequestBody Long id);

	@RequestMapping(value = "/boss/system/websiteNavArticle/updateById", method = RequestMethod.PUT)
	int updateById(@RequestBody WebsiteNavArticleQO qo);

	@RequestMapping(value = "/boss/system/websiteNavArticle/getById", method = RequestMethod.GET)
	WebsiteNavArticleVO getById(@RequestBody Long id);

	@RequestMapping(value = "/boss/system/websiteNavArticle/getByNavId/{navId}", method = RequestMethod.GET)
	WebsiteNavArticleVO getByNavId(@PathVariable(value = "navId") Long navId);
}
