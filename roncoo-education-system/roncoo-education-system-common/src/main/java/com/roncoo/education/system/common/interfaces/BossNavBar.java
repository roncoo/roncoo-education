package com.roncoo.education.system.common.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.system.common.bean.qo.NavBarQO;
import com.roncoo.education.system.common.bean.vo.NavBarVO;
import com.roncoo.education.util.base.Page;

/**
 * 头部导航
 *
 * @author wuyun
 */
public interface BossNavBar {

	@RequestMapping(value = "/boss/system/navBar/listForPage", method = RequestMethod.POST)
	Page<NavBarVO> listForPage(@RequestBody NavBarQO qo);

	@RequestMapping(value = "/boss/system/navBar/save", method = RequestMethod.POST)
	int save(@RequestBody NavBarQO qo);

	@RequestMapping(value = "/boss/system/navBar/deleteById", method = RequestMethod.DELETE)
	int deleteById(@RequestBody Long id);

	@RequestMapping(value = "/boss/system/navBar/updateById", method = RequestMethod.PUT)
	int updateById(@RequestBody NavBarQO qo);

	@RequestMapping(value = "/boss/system/navBar/get/{id}", method = RequestMethod.GET)
	NavBarVO getById(@PathVariable(value = "id") Long id);

}
