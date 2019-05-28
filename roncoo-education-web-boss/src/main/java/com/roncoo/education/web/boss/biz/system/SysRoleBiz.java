package com.roncoo.education.web.boss.biz.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.system.common.bean.qo.SysRoleQO;
import com.roncoo.education.system.common.bean.vo.SysRoleVO;
import com.roncoo.education.system.feign.IBossSysRole;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Page;

/**
 * 角色信息
 *
 * @author wujing
 */
@Component
public class SysRoleBiz extends BaseBiz {

	@Autowired
	private IBossSysRole bossSysRole;

	public Page<SysRoleVO> listForPage(SysRoleQO qo) {
		return bossSysRole.listForPage(qo);
	}

	public int save(SysRoleQO qo) {
		return bossSysRole.save(qo);
	}

	public int deleteById(Long id) {
		return bossSysRole.deleteById(id);
	}

	public SysRoleVO getById(Long id) {
		return bossSysRole.getById(id);
	}

	public int updateById(SysRoleQO qo) {
		return bossSysRole.updateById(qo);
	}

}
