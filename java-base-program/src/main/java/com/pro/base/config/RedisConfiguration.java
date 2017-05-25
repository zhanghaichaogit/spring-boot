package com.pro.base.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created by zhanghaichao on 2017/5/10.
 */

public class RedisConfiguration extends CachingConfigurerSupport {

    Logger logger = LoggerFactory.getLogger(RedisConfiguration.class);

    //    @Value("${spring.redis.host}")
//    private String host;
//
//    @Value("${spring.redis.port}")
//    private int port;
//
//    @Value("${spring.redis.timeout}")
//    private int timeout;
//
//    @Value("${spring.redis.pool.max-idle}")
//    private int maxIdle;
//
//    @Value("${spring.redis.pool.max-wait}")
//    private long maxWaitMillis;
//
//    @Value("${spring.redis.password}")
//    private String password;
//
//    @Bean
//    public JedisPool redisPoolFactory() {
//        logger.info("JedisPool注入成功！！");
//        logger.info("redis地址：" + host + ":" + port + "  " + password);
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxIdle(maxIdle);
//        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
//        JedisPool jedisPool;
//        if (password == null || password.isEmpty()) {
//            jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);
//        } else {
//            jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
//        }
//
//        return jedisPool;
//    }

    @Value("${spring.cache.redis.maxtime}")
    private int maxTime;

    @Bean
    public CacheManager cacheManager(RedisTemplate stringRedisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(stringRedisTemplate);

        // Number of seconds before expiration. Defaults to unlimited (0)
        cacheManager.setDefaultExpiration(maxTime); // Sets the default expire time (in seconds)
        return cacheManager;
    }

    /**
     * redis模板操作类
     *
     * @param redisConnectionFactory Springboot自动配置
     * @return StringRedisTemplate
     */
    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate(redisConnectionFactory);
        //设置序列化工具，这样ReportBean不需要实现Serializable接口
        template.setValueSerializer(new FastJson2JsonRedisSerializer<>(Object.class));
        //key的序列化使用StringRedisSerializer，字符串编码数据以String存储
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

}
