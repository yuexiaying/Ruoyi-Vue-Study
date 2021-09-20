package com.study.common.core.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * spring redis 工具类
 *
 * @author fjding
 * @date 2021/9/19
 */
@Component
public class RedisCache {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 缓存基本对象
     *
     * @param key
     * @param value
     * @param <T>
     */
    public <T> void setCacheObject(final String key, final T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 缓存基本对象，并指定缓存时间
     *
     * @param key
     * @param value
     * @param timeout
     * @param timeUnit
     * @param <T>
     */
    public <T> void setCacheObject(final String key, final T value, final long timeout, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 获得缓存对象
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> T getCacheObject(final String key) {
        ValueOperations<String, T> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    /**
     * 删除单个对象
     *
     * @param key
     */
    public boolean deleteObject(final String key)
    {
        return redisTemplate.delete(key);
    }

    /**
     * 设置有效时间
     *
     * @param key
     * @param timeout
     * @return
     */
    public boolean expire(final String key, final long timeout) {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置有效时间
     *
     * @param key
     * @param timeout
     * @return
     */
    public boolean expire(final String key, final long timeout, final TimeUnit timeUnit) {
        return redisTemplate.expire(key, timeout, timeUnit);
    }
}
