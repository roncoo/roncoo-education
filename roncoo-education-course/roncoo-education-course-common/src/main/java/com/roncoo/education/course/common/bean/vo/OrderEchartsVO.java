package com.roncoo.education.course.common.bean.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class OrderEchartsVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 总收入
	 */
	private List<BigDecimal> countPaidPrice;
	private List<Integer> countPaidPriceq;

	/**
	 * 总订单数
	 */
	private List<Integer> countOrders;

}