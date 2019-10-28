package com.roncoo.education.user.common;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.roncoo.education.util.tools.JSONUtil;

@Component
public class CacheRedis {

	private static final int MINUTE = 5;

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	/**
	 * 默认缓存5分钟
	 */
	public <T> T set(Object key, T t) {
		if (t != null) {
			redisTemplate.opsForValue().set(key, JSONUtil.toJSONString(t), MINUTE, TimeUnit.MINUTES);
		}
		return t;
	}

	/**
	 * 单位分钟
	 */
	public <T> T set(Object key, T t, Integer time) {
		if (t != null) {
			redisTemplate.opsForValue().set(key, JSONUtil.toJSONString(t), time, TimeUnit.MINUTES);
		}
		return t;
	}

	public <T> T get(Object key, Class<T> clazz) {
		if (redisTemplate.hasKey(key)) {
			return JSONUtil.parseObject(redisTemplate.opsForValue().get(key).toString(), clazz);
		}
		return null;
	}

	public void delete(Object key) {
		if (redisTemplate.hasKey(key)) {
			redisTemplate.delete(key);
		}
	}

}
