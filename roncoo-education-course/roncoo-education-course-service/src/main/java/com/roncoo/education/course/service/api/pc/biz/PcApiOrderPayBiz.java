package com.roncoo.education.course.service.api.pc.biz;

import com.roncoo.education.course.common.req.OrderPayPageREQ;
import com.roncoo.education.course.common.resq.OrderPayPageRESQ;
import com.roncoo.education.course.service.dao.OrderPayDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.OrderPay;
import com.roncoo.education.course.service.dao.impl.mapper.entity.OrderPayExample;
import com.roncoo.education.course.service.dao.impl.mapper.entity.OrderPayExample.Criteria;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 支付记录
 */
@Component
public class PcApiOrderPayBiz {

	@Autowired
	private OrderPayDao dao;

	public Result<Page<OrderPayPageRESQ>> list(OrderPayPageREQ req) {
		OrderPayExample example = new OrderPayExample();
		Criteria c = example.createCriteria();
		if (req.getOrderNo() != null) {
			c.andOrderNoEqualTo(req.getOrderNo());
		}
		example.setOrderByClause(" id desc ");
		Page<OrderPay> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
		return Result.success(PageUtil.transform(page, OrderPayPageRESQ.class));
	}

}
