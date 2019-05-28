package com.roncoo.education.web.boss.biz.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.system.common.bean.qo.SysUserQO;
import com.roncoo.education.system.common.bean.vo.SysUserVO;
import com.roncoo.education.system.feign.IBossSysUser;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Page;

/**
 * 后台用户信息
 *
 * @author wujing
 */
@Component
public class SysUserBiz extends BaseBiz {

	@Autowired
	private IBossSysUser bossSysUser;

	public Page<SysUserVO> listForPage(SysUserQO qo) {
		return bossSysUser.listForPage(qo);
	}

	public int save(SysUserQO qo) {
		return bossSysUser.save(qo);
	}

	public int deleteById(Long id) {
		return bossSysUser.deleteById(id);
	}

	public SysUserVO getById(Long id) {
		return bossSysUser.getById(id);
	}

	public int updateById(SysUserQO qo) {
		return bossSysUser.updateById(qo);
	}

}
