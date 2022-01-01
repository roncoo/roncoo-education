package com.roncoo.education.course.dao.impl;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.enums.OrderStatusEnum;
import com.roncoo.education.common.core.tools.DateUtil;
import com.roncoo.education.common.core.tools.IdWorker;
import com.roncoo.education.common.core.tools.SqlUtil;
import com.roncoo.education.course.dao.OrderInfoDao;
import com.roncoo.education.course.dao.impl.mapper.OrderInfoMapper;
import com.roncoo.education.course.dao.impl.mapper.entity.OrderInfo;
import com.roncoo.education.course.dao.impl.mapper.entity.OrderInfoExample;
import com.roncoo.education.course.feign.interfaces.qo.OrderInfoQO;
import com.roncoo.education.course.feign.interfaces.vo.OrderReportVO;
import com.roncoo.education.course.service.pc.resq.CountIncomeRESQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class OrderInfoDaoImpl implements OrderInfoDao {
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(OrderInfo record) {
        record.setId(IdWorker.getId());
        return this.orderInfoMapper.insertSelective(record);
    }

    @Override
    public int deleteById(Long id) {
        return this.orderInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(OrderInfo record) {
        return this.orderInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public OrderInfo getById(Long id) {
        return this.orderInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<OrderInfo> listForPage(int pageCurrent, int pageSize, OrderInfoExample example) {
        int count = this.orderInfoMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<OrderInfo>(count, totalPage, pageCurrent, pageSize, this.orderInfoMapper.selectByExample(example));
    }

    @Override
    public OrderInfo getByUserNoAndCourseId(Long userNo, Long courseId) {
        OrderInfoExample example = new OrderInfoExample();
        example.createCriteria().andUserNoEqualTo(userNo).andCourseIdEqualTo(courseId);
        example.setOrderByClause(" id desc ");
        List<OrderInfo> list = this.orderInfoMapper.selectByExample(example);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 根据订单编号查找订单信息
     */
    @Override
    public OrderInfo getByOrderNo(long orderNo) {
        OrderInfoExample example = new OrderInfoExample();
        OrderInfoExample.Criteria c = example.createCriteria();
        c.andOrderNoEqualTo(orderNo);
        List<OrderInfo> list = this.orderInfoMapper.selectByExample(example);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public BigDecimal sumLecturerUserNoAndData(Long lecturerUserNo, String date) {
        StringBuilder builder = new StringBuilder();
        builder.append("select sum(lecturer_profit) as sum from order_info where ");
        builder.append(" lecturer_user_no = ").append(lecturerUserNo);
        builder.append(" and order_status = 2 ");
        builder.append(" and pay_time >= '").append(date).append(" 00:00:00' ");
        builder.append("and pay_time <= '").append(date).append(" 23:59:59'");
        String sql = builder.toString();
        Map<String, Object> map = jdbcTemplate.queryForMap(sql);
        BigDecimal paidPrice = BigDecimal.valueOf(0);
        if (!StringUtils.isEmpty(map.get("sum"))) {
            BigDecimal bd = new BigDecimal(String.valueOf(map.get("sum")));
            paidPrice = bd.setScale(2, RoundingMode.DOWN);
        }
        return paidPrice;
    }

    /**
     * 订单信息汇总（导出报表）
     */
    @Override
    public List<OrderReportVO> listForReport(OrderInfoQO orderInfoQO) {
        StringBuilder sql = new StringBuilder();
        sql.append("select lecturer_name, lecturer_user_no, course_name, course_id, count(*) as courseCount, sum(price_paid) as pricePaidSum from order_info where 1=1 ");
        sql.append(joinSql(orderInfoQO));
        sql.append(" group by lecturer_user_no, course_id ");
        List<OrderReportVO> resultList = jdbcTemplate.query(sql.toString(), new RowMapper<OrderReportVO>() {
            @Override
            public OrderReportVO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                OrderReportVO vo = new OrderReportVO();
                vo.setLecturerName(resultSet.getString("lecturer_name"));
                vo.setLecturerUserNo(resultSet.getLong("lecturer_user_no"));
                vo.setCourseName(resultSet.getString("course_name"));
                vo.setCourseCount(resultSet.getInt("courseCount"));
                vo.setCourseId(resultSet.getLong("course_id"));
                vo.setCountPaidprice(new BigDecimal(resultSet.getString("pricePaidSum")));
                return vo;
            }
        });
        return resultList;
    }

    /**
     * 统计时间段内机构的总订单数
     */
    @Override
    public Integer sumByCountOrders(String date) {
        StringBuilder builder = new StringBuilder();
        builder.append("select count(*) as count from order_info where");
        builder.append(" order_status = 2 ");
        builder.append("and pay_time >= '").append(date).append(" 00:00:00' ");
        builder.append("and pay_time <= '").append(date).append(" 23:59:59'");
        String sql = builder.toString();
        Integer count = 0;
        Map<String, Object> map = jdbcTemplate.queryForMap(sql);
        if (!StringUtils.isEmpty(map.get("count"))) {
            count = Integer.valueOf(String.valueOf(map.get("count")));
        }

        return count;
    }

    /**
     * 统计时间段内机构的总收入
     */
    @Override
    public BigDecimal sumByPayTime(String date) {
        StringBuilder builder = new StringBuilder();
        builder.append("select sum(price_paid) paidPrice from order_info where");
        builder.append(" order_status = 2 ");
        builder.append("and pay_time >= '").append(date).append(" 00:00:00' ");
        builder.append("and pay_time <= '").append(date).append(" 23:59:59'");
        String sql = builder.toString();

        Map<String, Object> map = jdbcTemplate.queryForMap(sql);
        BigDecimal paidPrice = BigDecimal.valueOf(0);
        if (!StringUtils.isEmpty(map.get("paidPrice"))) {
            BigDecimal bd = new BigDecimal(String.valueOf(map.get("paidPrice")));
            paidPrice = bd.setScale(2, RoundingMode.DOWN);
        }
        return paidPrice;
    }

    /**
     * 统计订单收入情况
     */
    @Override
    public CountIncomeRESQ countIncome(OrderInfoQO qo) {
        StringBuilder sql = new StringBuilder();
        sql.append("select sum(price_paid) as totalProfit, sum(lecturer_profit) as lecturerProfit, sum(platform_profit) as platformProfit from order_info where 1=1 ");
        sql.append(joinSql(qo));
        CountIncomeRESQ result = jdbcTemplate.queryForObject(sql.toString(), new RowMapper<CountIncomeRESQ>() {
            @Override
            public CountIncomeRESQ mapRow(ResultSet resultSet, int arg1) throws SQLException {
                CountIncomeRESQ o = new CountIncomeRESQ();
                if (StringUtils.isEmpty(resultSet.getBigDecimal("totalProfit"))) {
                    o.setTotalProfit(BigDecimal.ZERO);
                } else {
                    o.setTotalProfit(resultSet.getBigDecimal("totalProfit"));
                }

                if (StringUtils.isEmpty(resultSet.getBigDecimal("lecturerProfit"))) {
                    o.setLecturerProfit(BigDecimal.ZERO);
                } else {
                    o.setLecturerProfit(resultSet.getBigDecimal("lecturerProfit"));
                }

                if (StringUtils.isEmpty(resultSet.getBigDecimal("platformProfit"))) {
                    o.setPlatformProfit(BigDecimal.ZERO);
                } else {
                    o.setPlatformProfit(resultSet.getBigDecimal("platformProfit"));
                }
                return o;
            }
        });
        return result;
    }

    /**
     * 拼接sql查询条件
     *
     * @param bean
     * @return
     */
    private String joinSql(OrderInfoQO bean) {
        StringBuilder sql = new StringBuilder();
        if (StringUtils.hasText(bean.getRemark())) {
            sql.append(" and remark like '").append(SqlUtil.like(bean.getRemark())).append("'");
        }
        if (!StringUtils.isEmpty(bean.getId())) {
            sql.append(" and id = ").append(bean.getId());
        }
        if (!StringUtils.isEmpty(bean.getOrderNo())) {
            sql.append(" and order_no = ").append(bean.getOrderNo());
        }
        if (StringUtils.hasText(bean.getCourseName()) && !StringUtils.isEmpty(bean.getCourseId())) {
            sql.append(" and course_name like '").append(SqlUtil.like(bean.getCourseName())).append("'");
        }
        if (!StringUtils.isEmpty(bean.getCourseId())) {
            sql.append(" and course_id = '").append(bean.getCourseId()).append("'");
        }
        if (StringUtils.hasText(bean.getMobile())) {
            sql.append(" and mobile = ").append(bean.getMobile());
        }
        if (StringUtils.hasText(bean.getBeginPayTime())) {
            sql.append(" and pay_time >= '").append(DateUtil.format(DateUtil.parseDate(bean.getBeginPayTime(), "yyyy-MM-dd"), "yyyy-MM-dd HH:mm:ss")).append("'");
        }
        if (StringUtils.hasText(bean.getEndPayTime())) {
            sql.append(" and pay_time < '").append(DateUtil.format(DateUtil.addDate(DateUtil.parseDate(bean.getEndPayTime(), "yyyy-MM-dd"), 1), "yyyy-MM-dd HH:mm:ss")).append("'");
        }
        if (!StringUtils.isEmpty(bean.getLecturerUserNo())) {
            sql.append(" and lecturer_user_no = ").append(bean.getLecturerUserNo());
        }
        if (!StringUtils.isEmpty(bean.getTradeType())) {
            sql.append(" and trade_type = ").append(bean.getTradeType());
        }
        if (!StringUtils.isEmpty(bean.getChannelType())) {
            sql.append(" and channel_type = ").append(bean.getChannelType());
        }
        if (!StringUtils.isEmpty(bean.getLecturerName())) {
            sql.append(" and lecturer_name like '").append(SqlUtil.like(bean.getLecturerName())).append("'");
        }
        // 价格
        if (!StringUtils.isEmpty(bean.getBeginPaidPrice())) {
            sql.append(" and price_paid >= ").append(bean.getBeginPaidPrice());
        }
        // 价格
        if (!StringUtils.isEmpty(bean.getEndPaidPrice())) {
            sql.append(" and price_paid < ").append(bean.getEndPaidPrice());
        }
        if (!StringUtils.isEmpty(bean.getOrderStatus())) {
            sql.append(" and order_status = ").append(bean.getOrderStatus());
        } else {
            // 统计已经成功的订单
            sql.append(" and order_status = ").append(OrderStatusEnum.SUCCESS.getCode());
        }
        return sql.toString();
    }

}
