package com.roncoo.education.common.cache;

import com.roncoo.education.common.core.tools.JSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
            redisTemplate.opsForValue().set(key, JSUtil.toJSONString(t), MINUTE, TimeUnit.MINUTES);
        }
        return t;
    }

    /**
     * 默认缓存5分钟
     */
    public <T> T set(Object key, T t, int mimute) {
        if (t != null) {
            redisTemplate.opsForValue().set(key, JSUtil.toJSONString(t), mimute, TimeUnit.MINUTES);
        }
        return t;
    }

    public <T> T get(Object key, Class<T> clazz) {
        return JSUtil.parseObject(redisTemplate.opsForValue().get(key).toString(), clazz);
    }

    public void delete(Object key) {
        redisTemplate.delete(key);
    }

    public <T> List<T> list(Object key, Class<T> clazz) {
        return JSUtil.parseArray(redisTemplate.opsForValue().get(key).toString(), clazz);
    }

    public boolean hasKey(Object key) {
        return redisTemplate.hasKey(key);
    }

}
