package com.apex.holiday.intercept;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.apex.holiday.util.RedisUtil;
import java.util.ArrayList;
import java.util.List;


/**
 * @desc: holiday-provider
 * @author: yangcheng
 * @createTime: 2018年4月23日 上午11:22:57
 * @version: v1.0
 */
public class MethodCacheInterceptor implements MethodInterceptor {
    private Logger logger =
            LoggerFactory.getLogger(MethodCacheInterceptor.class);
    private RedisUtil redisUtil;
    private List<String> targetNamesList; // 不加入缓存的service名称
    private List<String> methodNamesList; // 不加入缓存的方法名称
    private Long defaultCacheExpireTime; // 缓存默认的过期时间
    private Long xxxRecordManagerTime; //
    private Long xxxSetRecordManagerTime; //

    /**
     * 初始化读取不需要加入缓存的类名和方法名称
     */
    public MethodCacheInterceptor() {
        try {
            // 分割字符串 这里没有加入任何方法
            String[] targetNames = {};
            String[] methodNames = {};

            // 加载过期时间设置
            defaultCacheExpireTime = 3600L;
            xxxRecordManagerTime = 60L;
            xxxSetRecordManagerTime = 60L;
            // 创建list
            targetNamesList = new ArrayList<String>(targetNames.length);
            methodNamesList = new ArrayList<String>(methodNames.length);
            Integer maxLen = targetNames.length > methodNames.length
                    ? targetNames.length : methodNames.length;
            // 将不需要缓存的类名和方法名添加到list中
            for (int i = 0; i < maxLen; i++) {
                if (i < targetNames.length) {
                    targetNamesList.add(targetNames[i]);
                }
                if (i < methodNames.length) {
                    methodNamesList.add(methodNames[i]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*  
     *(non-Javadoc)  
     * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)  
     */  
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object value = null;

        String targetName = invocation.getThis().getClass().getName();
        String methodName = invocation.getMethod().getName();
        // 不需要缓存的内容
        if (!isAddCache(targetName, methodName)) {
            // 执行方法返回结果
            return invocation.proceed();
        }
        Object[] arguments = invocation.getArguments();
        String key = getCacheKey(targetName, methodName, arguments);
        logger.debug("redisKey: " + key);
        try {
            // 判断是否有缓存
            if (redisUtil.exists(key)) {
                logger.info("******get value from redis, redisKey: " + key);
                return redisUtil.get(key);
            }
            // 写入缓存
            value = invocation.proceed();
            if (value != null) {
                final String tkey = key;
                final Object tvalue = value;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (tkey.startsWith(
                                "com.service.impl.xxxRecordManager")) {
                            redisUtil.set(tkey, tvalue, xxxRecordManagerTime);
                        } else if (tkey.startsWith(
                                "com.service.impl.xxxSetRecordManager")) {
                            redisUtil.set(tkey, tvalue,
                                    xxxSetRecordManagerTime);
                        } else {
                            redisUtil.set(tkey, tvalue, defaultCacheExpireTime);
                        }
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (value == null) {
                return invocation.proceed();
            }
        }
        return value;
    }

    /**  
     * 是否加入缓存
     * @author: yangcheng 
     * @createTime: 2018年4月23日 上午11:29:16    
     * @param targetName
     * @param methodName
     * @return boolean  
     */  
    private boolean isAddCache(String targetName, String methodName) {
        boolean flag = true;
        if (targetNamesList.contains(targetName)
                || methodNamesList.contains(methodName)) {
            flag = false;
        }
        return flag;
    }

    /**  
     * 创建缓存key
     * @author: yangcheng 
     * @createTime: 2018年4月23日 上午11:29:35    
     * @param targetName String
     * @param methodName String
     * @param arguments Object[]
     * @return String  
     */  
    private String getCacheKey(String targetName, String methodName,
            Object[] arguments) {
        StringBuffer sbu = new StringBuffer();
        sbu.append(targetName).append("_").append(methodName);
        if ((arguments != null) && (arguments.length != 0)) {
            for (int i = 0; i < arguments.length; i++) {
                sbu.append("_").append(arguments[i]);
            }
        }
        return sbu.toString();
    }

    /**  
     * set方法注入（见配置文件）
     * @author: yangcheng 
     * @createTime: 2018年4月23日 上午11:30:10    
     * @param redisUtil RedisUtil  
     */  
    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }
}
