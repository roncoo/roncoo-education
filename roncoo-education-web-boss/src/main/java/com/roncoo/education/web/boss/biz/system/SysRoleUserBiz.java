package com.roncoo.education.web.boss.biz.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.system.common.bean.qo.SysRoleUserQO;
import com.roncoo.education.system.common.bean.vo.SysRoleUserVO;
import com.roncoo.education.system.feign.IBossSysRoleUser;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Page;

/**
 * 角色用户关联表
 *
 * @author wujing
 */
@Component
public class SysRoleUserBiz extends BaseBiz {

	@Autowired
	private IBossSysRoleUser bossSysRoleUser;

	public Page<SysRoleUserVO> listForPage(SysRoleUserQO qo) {
		return bossSysRoleUser.listForPage(qo);
	}

	public int save(SysRoleUserQO qo) {
		return bossSysRoleUser.save(qo);
	}

	public int deleteById(Long id) {
		return bossSysRoleUser.deleteById(id);
	}

	public SysRoleUserVO getById(Long id) {
		return bossSysRoleUser.getById(id);
	}

	public int updateById(SysRoleUserQO qo) {
		return bossSysRoleUser.updateById(qo);
	}

}
