package com.roncoo.education.web.boss.common;

import java.nio.charset.Charset;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import com.roncoo.education.util.tools.JSONUtil;

/**
 */
@Configuration
@EnableCaching
public class ConfigurationRedis extends CachingConfigurerSupport {

	public static final String NAME = "boss";

	@Bean
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<Object, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(factory);
		template.setValueSerializer(new RedisJsonSerializer<>(Object.class));
		template.afterPropertiesSet();
		return template;
	}
}

class RedisJsonSerializer<T> implements RedisSerializer<T> {

	private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	private Class<T> clazz;

	public RedisJsonSerializer(Class<T> clazz) {
		super();
		this.clazz = clazz;
	}

	@Override
	public byte[] serialize(T t) throws SerializationException {
		if (t == null) {
			return new byte[0];
		}
		return JSONUtil.toJSONString(t).getBytes(DEFAULT_CHARSET);
	}

	@Override
	public T deserialize(byte[] bytes) throws SerializationException {
		if (bytes == null || bytes.length <= 0) {
			return null;
		}
		String str = new String(bytes, DEFAULT_CHARSET);
		return JSONUtil.parseObject(str, clazz);
	}
}
