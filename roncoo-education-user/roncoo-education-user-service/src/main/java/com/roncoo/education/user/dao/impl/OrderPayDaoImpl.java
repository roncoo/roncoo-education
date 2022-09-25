package com.roncoo.education.user.dao.impl;

import cn.hutool.core.collection.CollUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.IdWorker;
import com.roncoo.education.user.dao.OrderPayDao;
import com.roncoo.education.user.dao.impl.mapper.OrderPayMapper;
import com.roncoo.education.user.dao.impl.mapper.entity.OrderPay;
import com.roncoo.education.user.dao.impl.mapper.entity.OrderPayExample;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 订单支付信息表 服务实现类
 *
 * @author wujing
 * @date 2022-09-06
 */
@Repository
@RequiredArgsConstructor
public class OrderPayDaoImpl implements OrderPayDao {

    @NotNull
    private final OrderPayMapper mapper;

    @Override
    public int save(OrderPay record) {
        if (record.getId() == null) {
            record.setId(IdWorker.getId());
        }
        return this.mapper.insertSelective(record);
    }

    @Override
    public int deleteById(Long id) {
        return this.mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(OrderPay record) {
        record.setGmtCreate(null);
        return this.mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public OrderPay getById(Long id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<OrderPay> page(int pageCurrent, int pageSize, OrderPayExample example) {
        int count = this.mapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<>(count, totalPage, pageCurrent, pageSize, this.mapper.selectByExample(example));
    }

    @Override
    public List<OrderPay> listByExample(OrderPayExample example) {
        return this.mapper.selectByExample(example);
    }

    @Override
    public int countByExample(OrderPayExample example) {
        return this.mapper.countByExample(example);
    }

    @Override
    public OrderPay getBySerialNumber(Long serialNumber) {
        OrderPayExample example = new OrderPayExample();
        example.createCriteria().andSerialNumberEqualTo(serialNumber);
        example.setOrderByClause("id desc");
        List<OrderPay> orderPayList = this.mapper.selectByExample(example);
        if (CollUtil.isNotEmpty(orderPayList)) {
            return orderPayList.get(0);
        }
        return null;
    }

    @Override
    public int updateOrderStatusByOrderNo(Long orderNo, Integer orderStatus) {
        OrderPay record = new OrderPay();
        record.setOrderNo(orderNo);
        record.setOrderStatus(orderStatus);
        OrderPayExample example = new OrderPayExample();
        example.createCriteria().andOrderNoEqualTo(orderNo);
        return this.mapper.updateByExample(record, example);
    }
}
