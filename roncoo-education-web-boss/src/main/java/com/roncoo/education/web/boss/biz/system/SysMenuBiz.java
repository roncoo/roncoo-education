package com.roncoo.education.web.boss.biz.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.system.common.bean.qo.SysMenuQO;
import com.roncoo.education.system.common.bean.vo.SysMenuVO;
import com.roncoo.education.system.feign.IBossSysMenu;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Page;

/**
 * 菜单信息
 *
 * @author wujing
 */
@Component
public class SysMenuBiz extends BaseBiz {

	@Autowired
	private IBossSysMenu bossSysMenu;

	public Page<SysMenuVO> listForPage(SysMenuQO qo) {
		return bossSysMenu.listForPage(qo);
	}

	public int save(SysMenuQO qo) {
		return bossSysMenu.save(qo);
	}

	public int deleteById(Long id) {
		return bossSysMenu.deleteById(id);
	}

	public SysMenuVO getById(Long id) {
		return bossSysMenu.getById(id);
	}

	public int updateById(SysMenuQO qo) {
		return bossSysMenu.updateById(qo);
	}

}
