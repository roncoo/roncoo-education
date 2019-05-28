package com.roncoo.education.web.boss.biz.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.system.common.bean.qo.SysLogQO;
import com.roncoo.education.system.common.bean.vo.SysLogVO;
import com.roncoo.education.system.feign.IBossSysLog;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Page;

/**
 * 后台操作日志表
 *
 * @author wujing
 */
@Component
public class SysLogBiz extends BaseBiz {

	@Autowired
	private IBossSysLog bossSysLog;

	public Page<SysLogVO> listForPage(SysLogQO qo) {
		return bossSysLog.listForPage(qo);
	}

	public int save(SysLogQO qo) {
		return bossSysLog.save(qo);
	}

	public int deleteById(Long id) {
		return bossSysLog.deleteById(id);
	}

	public SysLogVO getById(Long id) {
		return bossSysLog.getById(id);
	}

	public int updateById(SysLogQO qo) {
		return bossSysLog.updateById(qo);
	}

}
