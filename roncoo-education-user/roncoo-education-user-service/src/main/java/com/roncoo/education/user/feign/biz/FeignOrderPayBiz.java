package com.roncoo.education.user.feign.biz;


import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.user.dao.OrderPayDao;
import com.roncoo.education.user.dao.impl.mapper.entity.OrderPay;
import com.roncoo.education.user.dao.impl.mapper.entity.OrderPayExample;
import com.roncoo.education.user.dao.impl.mapper.entity.OrderPayExample.Criteria;
import com.roncoo.education.user.feign.interfaces.qo.OrderPayEditQO;
import com.roncoo.education.user.feign.interfaces.qo.OrderPayPageQO;
import com.roncoo.education.user.feign.interfaces.qo.OrderPaySaveQO;
import com.roncoo.education.user.feign.interfaces.vo.OrderPayPageVO;
import com.roncoo.education.user.feign.interfaces.vo.OrderPayViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 订单支付信息表
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class FeignOrderPayBiz extends BaseBiz {

    @NotNull
    private final OrderPayDao dao;

    public Page<OrderPayPageVO> page(OrderPayPageQO qo) {
        OrderPayExample example = new OrderPayExample();
        Criteria c = example.createCriteria();
        example.setOrderByClause(" id desc ");
        Page<OrderPay> page = dao.page(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, OrderPayPageVO.class);
    }

    public int save(OrderPaySaveQO qo) {
        OrderPay record = BeanUtil.copyProperties(qo, OrderPay.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public int updateById(OrderPayEditQO qo) {
        OrderPay record = BeanUtil.copyProperties(qo, OrderPay.class);
        return dao.updateById(record);
    }

    public OrderPayViewVO getById(Long id) {
        OrderPay record = dao.getById(id);
        return BeanUtil.copyProperties(record, OrderPayViewVO.class);
    }
}
