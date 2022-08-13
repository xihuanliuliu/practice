package com.jd.edi.spring.cache;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.jd.edi.spring.exception.RedisException;
import com.jd.edi.spring.util.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.swing.text.StyledEditorKit;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class RedisUtil {

    private static final Logger log = LoggerFactory.getLogger(RedisUtil.class);

    private static final RedisTemplate<String, Object> REDIS_TEMPLATE = SpringContextUtils.getBean("redisTemplate",
            RedisTemplate.class);

    public static final StringRedisTemplate STRING_REDIS_TEMPLATE = SpringContextUtils.getBean("stringRedisTemplate",
            StringRedisTemplate.class);


    /**
     * 是否过期
     *
     * @param key
     * @param time
     * @return
     */
    public static Boolean expire(String key, long time) {
        if (key.contains(" ")) {
            throw new RedisException("msg", "400");
        }
        try {
            if (time > 0) {
                REDIS_TEMPLATE.expire(key, time, TimeUnit.SECONDS);
            }
            return Boolean.TRUE;
        } catch (Exception e) {
            log.error("expire error : {}", e.getMessage());
            return Boolean.FALSE;
        }
    }

    /**
     * 获取key的过期时间
     * @param key
     * @return
     */
    public static Long getExpireTime(String key) {
        if (key.contains(" ")) {
            throw new RedisException("");
        }
        return REDIS_TEMPLATE.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public static Boolean hasKey(String key) {
        if (key.contains(" ")) {
            throw new RedisException("");
        }
        try {
            REDIS_TEMPLATE.hasKey(key);
            return Boolean.TRUE;
        } catch (Exception e) {
            log.error("");
            return Boolean.FALSE;
        }
    }

    /**
     * 删除key
     * @param keys
     */
    public static void del(String... keys) {
        if (keys != null && keys.length > 0) {
            for (String key : keys) {
                if (key.contains(" ")) {
                    throw new RedisException("");
                }
            }

            if (keys.length == 1) {
                REDIS_TEMPLATE.delete(keys[0]);
            } else {
                REDIS_TEMPLATE.delete(Arrays.asList(keys));
            }
        }
    }


    //==========  String  ===========

    /**
     * 获取key
     * @param key
     * @param <T>
     * @return
     */
    public static <T> T getKey(String key) {
        return (T) REDIS_TEMPLATE.opsForValue().get(key);
    }

    /**
     * 设置key
     * @param key
     * @param object
     * @param time
     */
    public static void setKey(String key,Object object, long time) {
        if (key.contains(" ")) {
            throw new RedisException("");
        }

        try {
            if (time > 0) {
                REDIS_TEMPLATE.opsForValue().set(key, object, time);
            } else {
                REDIS_TEMPLATE.opsForValue().set(key, object);
            }
        } catch (Exception e) {
            log.error("");
        }
    }


    /**
     * 递增，value必须为int
     * @param key
     * @param delta
     */
    public static Long incr(String key, long delta) {
        if (key.contains("")){

        }
        if (delta < 0) {

        }
        return REDIS_TEMPLATE.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     * @param key 键
     * @param delta 要减少几(小于0)
     * @return 自减后的值
     */
    public static Long decr(String key, long delta) {
        if (key.contains(StrUtil.SPACE)) {
            throw new RedisException("");
        }
        if (delta < 0) {
            throw new RuntimeException("递减因子必须小于0");
        }
        return STRING_REDIS_TEMPLATE.opsForValue().increment(key, -delta);
    }

    /**
     * 普通缓存获取
     * @param key 键
     * @return 值
     */
    public static Long getLongValue(String key) {
        if (key == null) {
            return null;
        }
        if (key.contains(StrUtil.SPACE)) {
            throw new RedisException("");
        }
        String result = STRING_REDIS_TEMPLATE.opsForValue().get(key);
        if (result == null) {
            return null;
        }
        return Long.valueOf(result);
    }

    /**
     *
     *
     * GET /edi/sh_jey/1_
     * 批量删除缓存
     * @param keys
     */
    public static void deleteBatch(List<String> keys) {
        if (CollUtil.isEmpty(keys)) {
            return;
        }
        for (String key : keys) {
            if (key.contains(StrUtil.SPACE)) {
                throw new RedisException("");
            }
        }
        REDIS_TEMPLATE.delete(keys);
    }




}
