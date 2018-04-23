package com.apex.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**  
 * @desc: holiday-provider  
 * @author: yangcheng  
 * @createTime: 2018年4月23日 上午11:31:27    
 * @version: v1.0    
 */    
public class RedisUtil {
    private Logger logger = LoggerFactory.getLogger(RedisUtil.class);
    private RedisTemplate<Serializable, Object> redisTemplate;

    /**  
     *  批量删除对应的value
     * @author: yangcheng 
     * @createTime: 2018年4月23日 上午11:31:39    
     * @param keys String... 
     */  
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**  
     * 批量删除value
     * @author: yangcheng 
     * @createTime: 2018年4月23日 上午11:32:17    
     * @param pattern void  
     */  
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }

    /**  
     * 删除对应的value
     * @author: yangcheng 
     * @createTime: 2018年4月23日 上午11:32:59    
     * @param key void  
     */  
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**  
     * 判断缓存中是否有对应的key
     * @author: yangcheng 
     * @createTime: 2018年4月23日 上午11:33:23    
     * @param key String
     * @return boolean  
     */  
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**  
     * 读取缓存
     * @author: yangcheng 
     * @createTime: 2018年4月23日 上午11:34:40    
     * @param key String
     * @return Object  
     */  
    public Object get(final String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate
                .opsForValue();
        result = operations.get(key);
        return result;
    }

    
    /**  
     * 写入
     * @author: yangcheng 
     * @createTime: 2018年4月23日 上午11:35:07    
     * @param key String
     * @param value Object
     * @return boolean  
     */  
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate
                    .opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            logger.error("系统异常",e);
        }
        return result;
    }

    
    /**  
     * @author: yangcheng 
     * @createTime: 2018年4月23日 上午11:35:45    
     * @param key String
     * @param value Object
     * @param expireTime Long
     * @return boolean  
     */  
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate
                    .opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            logger.error("系统异常",e);
        }
        return result;
    }

    /**  
     * 注入
     * @author: yangcheng 
     * @createTime: 2018年4月23日 上午11:36:15    
     * @param redisTemplate void  
     */  
    public void setRedisTemplate(
            RedisTemplate<Serializable, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
