package com.roncoo.education.web.boss.biz.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.system.common.bean.qo.SysMenuRoleQO;
import com.roncoo.education.system.common.bean.vo.SysMenuRoleVO;
import com.roncoo.education.system.feign.IBossSysMenuRole;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Page;

/**
 * 菜单角色关联表
 *
 * @author wujing
 */
@Component
public class SysMenuRoleBiz extends BaseBiz {

	@Autowired
	private IBossSysMenuRole bossSysMenuRole;

	public Page<SysMenuRoleVO> listForPage(SysMenuRoleQO qo) {
		return bossSysMenuRole.listForPage(qo);
	}

	public int save(SysMenuRoleQO qo) {
		return bossSysMenuRole.save(qo);
	}

	public int deleteById(Long id) {
		return bossSysMenuRole.deleteById(id);
	}

	public SysMenuRoleVO getById(Long id) {
		return bossSysMenuRole.getById(id);
	}

	public int updateById(SysMenuRoleQO qo) {
		return bossSysMenuRole.updateById(qo);
	}

}
