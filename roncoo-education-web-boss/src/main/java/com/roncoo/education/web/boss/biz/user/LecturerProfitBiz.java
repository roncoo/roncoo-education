package com.roncoo.education.web.boss.biz.user;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.user.common.bean.qo.LecturerProfitQO;
import com.roncoo.education.user.common.bean.vo.LecturerProfitVO;
import com.roncoo.education.user.feign.IBossLecturerProfit;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.enums.ProfitStatusEnum;
import com.roncoo.education.web.boss.common.BizBoss;
import com.roncoo.education.web.boss.common.ReportExcelUtil;

/**
 * 讲师提现日志表
 *
 * @author wujing
 */
@Component
public class LecturerProfitBiz extends BizBoss {

	@Autowired
	private IBossLecturerProfit bossLecturerProfit;

	public Page<LecturerProfitVO> listForPage(LecturerProfitQO qo) {
		return bossLecturerProfit.listForPage(qo);
	}

	public int save(LecturerProfitQO qo) {
		return bossLecturerProfit.save(qo);
	}

	public int deleteById(Long id) {
		return bossLecturerProfit.deleteById(id);
	}

	public LecturerProfitVO getById(Long id) {
		return bossLecturerProfit.getById(id);
	}

	public int updateById(LecturerProfitQO qo) {
		return bossLecturerProfit.updateById(qo);
	}

	public int status(LecturerProfitQO qo) {
		return bossLecturerProfit.updateById(qo);
	}

	public void audit(String ids) {
		if (StringUtils.isNotBlank(ids)) {
			String[] id = ids.split(",");
			for (String i : id) {
				LecturerProfitQO qo = new LecturerProfitQO();
				qo.setId(Long.valueOf(i));
				qo.setProfitStatus(ProfitStatusEnum.SUCCESS.getCode());
				bossLecturerProfit.updateById(qo);
			}
		}
	}

	public void export(HttpServletResponse response, LecturerProfitQO qo) throws IOException {
		try {
			response.setContentType("application/vnd.ms-excel;charset=utf-8");// 设置强制下载不打开
			// 设置文件名
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("讲师分润报表", "utf-8") + ".xlsx");
		} catch (UnsupportedEncodingException e) {
			logger.error("导出文件失败", e);
		}
		Page<LecturerProfitVO> result = bossLecturerProfit.listForPage(qo);
		ReportExcelUtil.exportExcelForLecturerProfit(response, result);
	}

}
