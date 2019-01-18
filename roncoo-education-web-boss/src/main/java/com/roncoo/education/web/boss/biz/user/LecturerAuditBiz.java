package com.roncoo.education.web.boss.biz.user;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.user.common.bean.qo.LecturerAuditQO;
import com.roncoo.education.user.common.bean.vo.LecturerAuditVO;
import com.roncoo.education.user.feign.IBossLecturerAudit;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Page;

/**
 * 讲师信息-审核
 *
 * @author wujing
 */
@Component
public class LecturerAuditBiz extends BaseBiz {

	@Autowired
	private IBossLecturerAudit bossLecturerAudit;

	public Page<LecturerAuditVO> listForPage(LecturerAuditQO qo) {
		return bossLecturerAudit.listForPage(qo);
	}

	public int save(LecturerAuditQO qo) {
		return bossLecturerAudit.save(qo);
	}

	public int deleteById(Long id) {
		return bossLecturerAudit.deleteById(id);
	}

	public LecturerAuditVO getById(Long id) {
		return bossLecturerAudit.getById(id);
	}

	public int updateById(LecturerAuditQO qo) {
		qo.setLecturerProportion(qo.getLecturerProportion().divide(BigDecimal.valueOf(100L)));
		return bossLecturerAudit.updateById(qo);
	}

	public int audit(LecturerAuditQO qo) {
		return bossLecturerAudit.audit(qo);
	}

	public int updateStatusId(LecturerAuditQO qo) {
		return bossLecturerAudit.updateById(qo);
	}

	public LecturerAuditVO checkUserAndLecturer(LecturerAuditQO qo) {
		return bossLecturerAudit.checkUserAndLecturer(qo);
	}

}
