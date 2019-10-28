package com.roncoo.education.user.feign.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserEchartsVO implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private List<BigDecimal> counts;

	private List<Integer> count;
	private String gmtCreate;

}
