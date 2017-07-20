package com.pro.base.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by zhanghaichao on 2017/6/19.
 */
@Configuration
@EnableRedisHttpSession
public class RedisSessionConfig {

}
