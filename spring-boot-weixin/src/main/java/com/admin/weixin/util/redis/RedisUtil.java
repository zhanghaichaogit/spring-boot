package com.admin.weixin.util.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 * @author zhanghaichao on 2017/8/21.
 */
@Component
public class RedisUtil {

  @Resource
  private StringRedisTemplate stringRedisTemplateRes;

  private static StringRedisTemplate stringRedisTemplate;

  public static void set(String key, String value, Long time) {
    stringRedisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
  }

  public static String get(String key) {
    return stringRedisTemplate.opsForValue().get(key);
  }

  @PostConstruct
  public void init() {
    this.stringRedisTemplate = stringRedisTemplateRes;
  }
}
