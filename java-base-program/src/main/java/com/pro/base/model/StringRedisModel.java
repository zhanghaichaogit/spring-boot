package com.pro.base.model;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhanghaichao on 2017/5/23.
 */

public class StringRedisModel {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 毫无父子兄弟关系的两个类，却能互相注入，原理：Spring的Editor机制
     * ValueOperationsEditor源码中，对StringRedisTemplate的接口RedisOperations进行转换。
     * public void setValue(Object value) {
     * if(value instanceof RedisOperations) {
     * super.setValue(((RedisOperations)value).opsForValue());
     * } else {
     * throw new IllegalArgumentException("Editor supports only conversion of type " +
     * RedisOperations.class);
     * }
     * }
     *
     * @see <a href="http://www.cnblogs.com/chanedi/p/4135303.html">Spring Editor机制</a>
     */
    @Resource(name = "stringRedisTemplate")
    private ValueOperations<String, String> valOpsStr;

    private void setValToRedis(String key, String json, int timeout) {
        //stringRedisTemplate.opsForValue().set....
        if (timeout > 0) {
            valOpsStr.set(key, json, timeout, TimeUnit.SECONDS);
        } else {
            valOpsStr.set(key, json);
        }
    }

    /**
     * 存储字符串到Redis
     *
     * @param key   存储Key值
     * @param value 存储内容
     */
    public void set(String key, String value) {
        this.set(key, value, -1);
    }

    /**
     * 存储字符串到Redis
     *
     * @param key     存储Key值
     * @param value   存储内容
     * @param timeout 过期时间，单位秒，-1 不过期
     */
    public void set(String key, String value, int timeout) {
        this.setValToRedis(key, value, timeout);
    }

    /**
     * 从Redis获取存储的内容
     *
     * @param key 存储Key值
     * @return 存储内容
     */
    public String get(String key) {
        return valOpsStr.get(key);
    }

    /**
     * 删除Redis数据
     *
     * @param key 存储Key值
     */
    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    /**
     * 判断Redis是否存在Key
     *
     * @param key 存储Key值
     * @return true - 存在
     */
    public boolean exist(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    /**
     * 获取存活时间
     * <ul><li>-2：key值不存在</li>
     * <li>-1：无过期时间</li>
     * <li>N：N秒过期</li></ul>
     *
     * @param key 存储Key值
     */
    public long getExpire(String key) {
        return stringRedisTemplate.getExpire(key);
    }
}
