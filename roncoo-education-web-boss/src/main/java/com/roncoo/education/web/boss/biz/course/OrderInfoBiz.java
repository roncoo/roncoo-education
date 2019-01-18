package com.roncoo.education.web.boss.biz.course;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.course.common.bean.qo.OrderInfoQO;
import com.roncoo.education.course.common.bean.vo.CountIncomeVO;
import com.roncoo.education.course.common.bean.vo.OrderInfoVO;
import com.roncoo.education.course.feign.IBossOrderInfo;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.web.boss.common.ReportExcelUtil;

/**
 * 订单信息表
 *
 * @author wujing
 */
@Component
public class OrderInfoBiz extends BaseBiz {

	@Autowired
	private IBossOrderInfo bossOrderInfo;

	public Page<OrderInfoVO> listForPage(OrderInfoQO qo) {
		return bossOrderInfo.listForPage(qo);
	}

	public int save(OrderInfoQO qo) {
		return bossOrderInfo.save(qo);
	}

	public int deleteById(Long id) {
		return bossOrderInfo.deleteById(id);
	}

	public OrderInfoVO getById(Long id) {
		return bossOrderInfo.getById(id);
	}

	public int updateById(OrderInfoQO qo) {
		return bossOrderInfo.updateById(qo);
	}

	public void orderExport(OrderInfoQO orderInfoQO, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=utf-8");// 设置强制下载不打开
			// 设置文件名
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("订单报表-" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()), "utf-8") + ".xlsx");
			ReportExcelUtil.exportExcelForOrderInfo(response, bossOrderInfo, orderInfoQO);
		} catch (Exception e) {
			logger.error("导出报表错误", e);
		}
	}

	public CountIncomeVO countIncome(OrderInfoQO qo) {
		return bossOrderInfo.countIncome(qo);
	}

}