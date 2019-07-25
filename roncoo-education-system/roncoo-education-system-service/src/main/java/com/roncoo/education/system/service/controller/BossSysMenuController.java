package com.roncoo.education.system.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.system.common.bean.qo.SysMenuQO;
import com.roncoo.education.system.common.bean.vo.SysMenuVO;
import com.roncoo.education.system.common.interfaces.BossSysMenu;
import com.roncoo.education.system.service.controller.biz.BossSysMenuBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;

/**
 * 菜单信息
 *
 * @author wujing
 */
@RestController
public class BossSysMenuController extends BaseController implements BossSysMenu {

	@Autowired
	private BossSysMenuBiz biz;

	@Override
	public Page<SysMenuVO> listForPage(@RequestBody SysMenuQO qo) {
		return biz.listForPage(qo);
	}

	@Override
	public int save(@RequestBody SysMenuQO qo) {
		return biz.save(qo);
	}

	@Override
	public int deleteById(@RequestBody Long id) {
		return biz.deleteById(id);
	}

	@Override
	public int updateById(@RequestBody SysMenuQO qo) {
		return biz.updateById(qo);
	}

	@Override
	public SysMenuVO getById(@RequestBody Long id) {
		return biz.getById(id);
	}

	@Override
	public List<SysMenuVO> listByUserAndMenu(@RequestBody Long userNo) {
		return biz.listByUserAndMenu(userNo);
	}

}
