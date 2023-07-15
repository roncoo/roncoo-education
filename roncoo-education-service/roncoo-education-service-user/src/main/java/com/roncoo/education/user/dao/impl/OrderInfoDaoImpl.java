package com.roncoo.education.user.dao.impl;

import cn.hutool.core.collection.CollUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.IdWorker;
import com.roncoo.education.user.dao.OrderInfoDao;
import com.roncoo.education.user.dao.impl.mapper.OrderInfoMapper;
import com.roncoo.education.user.dao.impl.mapper.entity.OrderInfo;
import com.roncoo.education.user.dao.impl.mapper.entity.OrderInfoExample;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 订单信息表 服务实现类
 *
 * @author wujing
 * @date 2022-09-06
 */
@Repository
@RequiredArgsConstructor
public class OrderInfoDaoImpl implements OrderInfoDao {

    @NotNull
    private final OrderInfoMapper mapper;

    @Override
    public int save(OrderInfo record) {
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
    public int updateById(OrderInfo record) {
        record.setGmtCreate(null);
        record.setGmtModified(null);
        return this.mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public OrderInfo getById(Long id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<OrderInfo> page(int pageCurrent, int pageSize, OrderInfoExample example) {
        int count = this.mapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<>(count, totalPage, pageCurrent, pageSize, this.mapper.selectByExample(example));
    }

    @Override
    public List<OrderInfo> listByExample(OrderInfoExample example) {
        return this.mapper.selectByExample(example);
    }

    @Override
    public int countByExample(OrderInfoExample example) {
        return this.mapper.countByExample(example);
    }

    @Override
    public OrderInfo getByUserAndCourseId(Long userId, Long courseId) {
        OrderInfoExample example = new OrderInfoExample();
        example.createCriteria().andUserIdEqualTo(userId).andCourseIdEqualTo(courseId);
        example.setOrderByClause("id desc");
        List<OrderInfo> orderInfos = this.mapper.selectByExample(example);
        if (CollUtil.isNotEmpty(orderInfos)) {
            return orderInfos.get(0);
        }
        return null;
    }

    @Override
    public OrderInfo getByOrderNo(Long orderNo) {
        OrderInfoExample example = new OrderInfoExample();
        example.createCriteria().andOrderNoEqualTo(orderNo);
        example.setOrderByClause("id desc");
        List<OrderInfo> orderInfos = this.mapper.selectByExample(example);
        if (CollUtil.isNotEmpty(orderInfos)) {
            return orderInfos.get(0);
        }
        return null;
    }
}
