package com.roncoo.education.user.feign.biz;


import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.user.dao.OrderInfoDao;
import com.roncoo.education.user.dao.impl.mapper.entity.OrderInfo;
import com.roncoo.education.user.dao.impl.mapper.entity.OrderInfoExample;
import com.roncoo.education.user.dao.impl.mapper.entity.OrderInfoExample.Criteria;
import com.roncoo.education.user.feign.interfaces.qo.OrderInfoEditQO;
import com.roncoo.education.user.feign.interfaces.qo.OrderInfoPageQO;
import com.roncoo.education.user.feign.interfaces.qo.OrderInfoSaveQO;
import com.roncoo.education.user.feign.interfaces.vo.OrderInfoPageVO;
import com.roncoo.education.user.feign.interfaces.vo.OrderInfoViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 订单信息表
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class FeignOrderInfoBiz extends BaseBiz {

    @NotNull
    private final OrderInfoDao dao;

    public Page<OrderInfoPageVO> page(OrderInfoPageQO qo) {
        OrderInfoExample example = new OrderInfoExample();
        Criteria c = example.createCriteria();
        example.setOrderByClause(" id desc ");
        Page<OrderInfo> page = dao.page(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, OrderInfoPageVO.class);
    }

    public int save(OrderInfoSaveQO qo) {
        OrderInfo record = BeanUtil.copyProperties(qo, OrderInfo.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public int updateById(OrderInfoEditQO qo) {
        OrderInfo record = BeanUtil.copyProperties(qo, OrderInfo.class);
        return dao.updateById(record);
    }

    public OrderInfoViewVO getById(Long id) {
        OrderInfo record = dao.getById(id);
        return BeanUtil.copyProperties(record, OrderInfoViewVO.class);
    }
}
