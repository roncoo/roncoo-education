/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.course.common.es;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.roncoo.education.util.base.Page;

/**
 * 分页
 *
 * @author wujing
 * @param <T>
 */
public final class EsPageUtil<T extends Serializable> implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(EsPageUtil.class);

	private static final long serialVersionUID = 1L;

	private EsPageUtil() {
	}

	public static <T extends Serializable> Page<T> transform(org.springframework.data.domain.Page<EsCourse> page, Class<T> classType) {
		Page<T> pb = new Page<>();
		try {
			pb.setList(copyList(page.getContent(), classType));
		} catch (Exception e) {
			logger.error("transform error", e);
		}
		pb.setPageCurrent(page.getPageable().getPageNumber() + 1);
		pb.setPageSize(page.getPageable().getPageSize());
		pb.setTotalCount((int) page.getTotalElements());
		pb.setTotalPage(page.getTotalPages());
		return pb;
	}

	/**
	 * @param source
	 * @param clazz
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	public static <T> List<T> copyList(List<?> source, Class<T> clazz) {
		if (source == null || source.size() == 0) {
			return Collections.emptyList();
		}
		List<T> res = new ArrayList<>(source.size());
		for (Object o : source) {
			try {
				T t = clazz.newInstance();
				res.add(t);
				BeanUtils.copyProperties(o, t);
			} catch (Exception e) {
				logger.error("copyList error", e);
			}
		}
		return res;
	}

}
