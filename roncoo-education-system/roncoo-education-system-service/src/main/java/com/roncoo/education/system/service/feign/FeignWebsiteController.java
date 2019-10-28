package com.roncoo.education.system.service.feign;

import com.roncoo.education.system.feign.interfaces.IFeignWebsite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.roncoo.education.system.feign.qo.WebsiteQO;
import com.roncoo.education.system.feign.vo.WebsiteVO;
import com.roncoo.education.system.service.feign.biz.FeignWebsiteBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;

/**
 * 站点信息
 *
 * @author wuyun
 */
@RestController
public class FeignWebsiteController extends BaseController implements IFeignWebsite {

	@Autowired
	private FeignWebsiteBiz biz;

	@Override
	public Page<WebsiteVO> listForPage(@RequestBody WebsiteQO qo) {
		return biz.listForPage(qo);
	}

	@Override
	public int save(@RequestBody WebsiteQO qo) {
		return biz.save(qo);
	}

	@Override
	public int deleteById(@RequestBody Long id) {
		return biz.deleteById(id);
	}

	@Override
	public int updateById(@RequestBody WebsiteQO qo) {
		return biz.updateById(qo);
	}

	@Override
	public WebsiteVO getById(@PathVariable(value = "id") Long id) {
		return biz.getById(id);
	}

	@Override
	public WebsiteVO getWebsite() {
		return biz.getWebsite();
	}

}
