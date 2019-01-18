package com.roncoo.education.web.boss.biz.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.system.common.bean.qo.NavBarQO;
import com.roncoo.education.system.common.bean.vo.NavBarVO;
import com.roncoo.education.system.feign.IBossNavBar;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Page;

/**
 * 头部导航 
 *
 * @author wuyun
 */
@Component
public class NavBarBiz extends BaseBiz{

	@Autowired
	private IBossNavBar bossNavBar;

	public Page<NavBarVO> listForPage(NavBarQO qo) {
        return bossNavBar.listForPage(qo);
	}

	public int save(NavBarQO qo) {
		return bossNavBar.save(qo);
	}

	public int deleteById(Long id) {
		return bossNavBar.deleteById(id);
	}

	public NavBarVO getById(Long id) {
		return bossNavBar.getById(id);
	}

	public int updateById(NavBarQO qo) {
		return bossNavBar.updateById(qo);
	}

	public int updateStatusId(NavBarQO qo) {
		return bossNavBar.updateById(qo);
	}

}
