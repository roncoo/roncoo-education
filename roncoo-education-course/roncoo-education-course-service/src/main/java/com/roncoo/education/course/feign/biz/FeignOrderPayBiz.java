package com.roncoo.education.course.feign.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.course.dao.OrderPayDao;
import com.roncoo.education.course.dao.impl.mapper.entity.OrderPay;
import com.roncoo.education.course.dao.impl.mapper.entity.OrderPayExample;
import com.roncoo.education.course.dao.impl.mapper.entity.OrderPayExample.Criteria;
import com.roncoo.education.course.feign.interfaces.qo.OrderPayQO;
import com.roncoo.education.course.feign.interfaces.vo.OrderPayVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 订单支付信息表
 *
 * @author wujing
 */
@Component
public class FeignOrderPayBiz {

    @Autowired
    private OrderPayDao dao;

    public Page<OrderPayVO> listForPage(OrderPayQO qo) {
        OrderPayExample example = new OrderPayExample();
        Criteria c = example.createCriteria();
        if (qo.getOrderNo() != null) {
            c.andOrderNoEqualTo(qo.getOrderNo());
        }
        example.setOrderByClause(" id desc ");
        Page<OrderPay> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, OrderPayVO.class);
    }

    public int save(OrderPayQO qo) {
        OrderPay record = BeanUtil.copyProperties(qo, OrderPay.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public OrderPayVO getById(Long id) {
        OrderPay record = dao.getById(id);
        return BeanUtil.copyProperties(record, OrderPayVO.class);
    }

    public int updateById(OrderPayQO qo) {
        OrderPay record = BeanUtil.copyProperties(qo, OrderPay.class);
        return dao.updateById(record);
    }

}
