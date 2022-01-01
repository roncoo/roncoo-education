package com.roncoo.education.course.service.pc.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.course.dao.OrderPayDao;
import com.roncoo.education.course.dao.impl.mapper.entity.OrderPay;
import com.roncoo.education.course.dao.impl.mapper.entity.OrderPayExample;
import com.roncoo.education.course.dao.impl.mapper.entity.OrderPayExample.Criteria;
import com.roncoo.education.course.service.pc.req.OrderPayPageREQ;
import com.roncoo.education.course.service.pc.resq.OrderPayPageRESQ;
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
