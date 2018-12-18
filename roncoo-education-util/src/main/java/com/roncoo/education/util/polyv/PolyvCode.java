/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.polyv;

import java.io.Serializable;

public class PolyvCode implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long userNo;

	private Long periodId;

	public Long getUserNo() {
		return userNo;
	}

	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}

	public Long getPeriodId() {
		return periodId;
	}

	public void setPeriodId(Long periodId) {
		this.periodId = periodId;
	}

}
