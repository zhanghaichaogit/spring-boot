package com.cache.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableAutoConfiguration
@ImportResource(locations = {"classpath:config/conf.xml",//导入本项目配置
        "classpath*:config/base-conf.xml",//导入base项目的默认配置
        "classpath*:config/spring-cache-redis.xml",//导入base项目的cacheRedis配置
        "classpath*:config/spring-db-setting.xml",//导入base项目的数据库配置
        "classpath*:config/spring-durid-bean.xml"})//导入base项目的durid项目配置
@EnableCaching
public class SimpleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SimpleApplication.class, args);
    }
}
