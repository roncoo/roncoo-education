package com.roncoo.education.common.cache;

import com.roncoo.education.common.core.tools.JSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class CacheRedis {

    @Value("${spring.cache.redis.time-to-live:60000}")
    private int timeToLive;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public StringRedisTemplate getStringRedisTemplate() {
        return stringRedisTemplate;
    }

    /**
     * 默认缓存1分钟
     */
    public <T> T set(String key, T t) {
        if (t != null) {
            String value = JSUtil.toJsonString(t);
            if (t instanceof String) {
                value = t.toString();
            }
            stringRedisTemplate.opsForValue().set(key, value, timeToLive, TimeUnit.MILLISECONDS);
        }
        return t;
    }

    /**
     * 默认缓存5分钟
     */
    public <T> T set(String key, T t, int time, TimeUnit timeUnit) {
        if (t != null) {
            String value = JSUtil.toJsonString(t);
            if (t instanceof String) {
                value = t.toString();
            }
            stringRedisTemplate.opsForValue().set(key, value, time, timeUnit);
        }
        return t;
    }

    public String get(String key) {
        if (null != key) {
            return stringRedisTemplate.opsForValue().get(key);
        }
        return null;
    }

    public <T> T getByJson(String key, Class<T> clazz) {
        String value = get(key);
        if (StringUtils.hasText(value)) {
            return JSUtil.parseObject(value, clazz);
        }
        return null;
    }

    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    public <T> List<T> listByJson(String key, Class<T> clazz) {
        return JSUtil.parseArray(get(key), clazz);
    }

}
