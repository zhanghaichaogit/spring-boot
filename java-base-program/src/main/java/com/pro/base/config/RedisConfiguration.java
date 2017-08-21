package com.pro.base.config;

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
 * @author zhanghaichao on 2017/5/10.
 */

public class RedisConfiguration extends CachingConfigurerSupport {

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
   * redis模板操作类 这个是为了将存在redis里面的二进制文件转成实体对应的json字符串 可以不要这里
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
