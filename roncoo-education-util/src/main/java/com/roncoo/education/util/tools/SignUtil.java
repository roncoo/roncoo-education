/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.tools;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wujing
 */
public final class SignUtil {

	protected static final Logger logger = LoggerFactory.getLogger(SignUtil.class);

	private SignUtil() {

	}

	public static String getByLecturer(BigDecimal totalIncome, BigDecimal historyMoney, BigDecimal enableBalances, BigDecimal freezeBalances) {
		int t = totalIncome.add(historyMoney).add(enableBalances).add(freezeBalances).multiply(new BigDecimal(100)).intValue();
		return MD5Util.MD5("roncoo" + t);
	}

}
