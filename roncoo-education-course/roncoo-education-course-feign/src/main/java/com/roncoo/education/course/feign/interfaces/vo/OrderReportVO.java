package com.roncoo.education.course.feign.interfaces.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单汇总
 *
 * @author LHR
 */
@Data
@Accessors(chain = true)
public class OrderReportVO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 讲师用户编号
     */
    private Long lecturerUserNo;
    /**
     * 讲师名称
     */
    private String lecturerName;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 课程编号
     */
    private Long courseId;
    /**
     * 课程销售数量
     */
    private Integer courseCount;
    /**
     * 销售总金额
     */
    private BigDecimal countPaidprice;
}
