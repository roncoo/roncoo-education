package com.roncoo.education.user.common.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.user.common.bean.qo.PlatformQO;
import com.roncoo.education.user.common.bean.vo.PlatformVO;
import com.roncoo.education.util.base.Page;

/**
 * 平台信息
 *
 * @author wujing
 */
public interface BossPlatform {

	@RequestMapping(value = "/boss/user/platform/listForPage", method = RequestMethod.POST)
	Page<PlatformVO> listForPage(@RequestBody PlatformQO qo);

	@RequestMapping(value = "/boss/user/platform/save", method = RequestMethod.POST)
	int save(@RequestBody PlatformQO qo);

	@RequestMapping(value = "/boss/user/platform/delete/{id}", method = RequestMethod.DELETE)
	int deleteById(@PathVariable(value = "id") Long id);

	@RequestMapping(value = "/boss/user/platform/update", method = RequestMethod.PUT)
	int updateById(@RequestBody PlatformQO qo);

	@RequestMapping(value = "/boss/user/platform/get/{id}", method = RequestMethod.GET)
	PlatformVO getById(@PathVariable(value = "id") Long id);

	@RequestMapping(value = "/boss/user/platform/getByClientId/{clientId}", method = RequestMethod.GET)
	PlatformVO getByClientId(@PathVariable(value = "clientId") String clientId);

}
