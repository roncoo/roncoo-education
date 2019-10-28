package com.roncoo.education.course.common.dto.auth;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单信息表
 * </p>
 *
 * @author 		YZJ
 */
@Data
@Accessors(chain = true)
public class AuthOrderInfoLecturerIncomeDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 讲师收入
	 */
	@ApiModelProperty(value = "讲师收入")
	private List<BigDecimal> lecturerProfit;

}
