package com.roncoo.education.common.cache;

import com.roncoo.education.common.core.tools.JSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class CacheRedis {

    @Value("${spring.cache.redis.time-to-live:60000}")
    private int timeToLive;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 默认缓存5分钟
     */
    public <T> T set(Object key, T t) {
        if (t != null) {
            if (t instanceof String) {
                redisTemplate.opsForValue().set(key, t, timeToLive, TimeUnit.MICROSECONDS);
            }
            redisTemplate.opsForValue().set(key, JSUtil.toJSONString(t), timeToLive, TimeUnit.MICROSECONDS);
        }
        return t;
    }

    /**
     * 默认缓存5分钟
     */
    public <T> T set(Object key, T t, int time, TimeUnit timeUnit) {
        if (t != null) {
            redisTemplate.opsForValue().set(key, JSUtil.toJSONString(t), time, timeUnit);
        }
        return t;
    }

    public String get(String key) {
        if (null != key) {
            return redisTemplate.opsForValue().get(key).toString();
        }
        return null;
    }

    public <T> T getByJson(Object key, Class<T> clazz) {
        return JSUtil.parseObject(redisTemplate.opsForValue().get(key).toString(), clazz);
    }

    public void delete(Object key) {
        redisTemplate.delete(key);
    }

    public <T> List<T> listByJson(Object key, Class<T> clazz) {
        return JSUtil.parseArray(redisTemplate.opsForValue().get(key).toString(), clazz);
    }

    public boolean hasKey(Object key) {
        return redisTemplate.hasKey(key);
    }

}
