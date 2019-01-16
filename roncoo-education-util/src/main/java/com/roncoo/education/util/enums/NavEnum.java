/**
 * Copyright 2015-2017 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NavEnum {

	INDEX("/index", "首页"), COURSE("/list", "录播中心"), RECRUIT("/recruit", "讲师招募");

	public String code;

	public String desc;

	public static NavEnum getByDesc(String code) {
		NavEnum tce = null;
		for (NavEnum em : NavEnum.values()) {
			if (em.getCode().equals(code)) {
				tce = em;
			}
		}
		if (tce == null) {
			return null;
		}
		return tce;
	}
}
