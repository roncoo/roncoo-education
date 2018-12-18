package com.roncoo.education.course.service.dao.impl.mapper;

import com.roncoo.education.course.service.dao.impl.mapper.entity.OrderPay;
import com.roncoo.education.course.service.dao.impl.mapper.entity.OrderPayExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderPayMapper {
    int countByExample(OrderPayExample example);

    int deleteByExample(OrderPayExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderPay record);

    int insertSelective(OrderPay record);

    List<OrderPay> selectByExample(OrderPayExample example);

    OrderPay selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderPay record, @Param("example") OrderPayExample example);

    int updateByExample(@Param("record") OrderPay record, @Param("example") OrderPayExample example);

    int updateByPrimaryKeySelective(OrderPay record);

    int updateByPrimaryKey(OrderPay record);
}