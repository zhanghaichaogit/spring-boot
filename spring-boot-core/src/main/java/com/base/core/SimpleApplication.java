package com.base.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * 本项目默认即成了单redis
 */

@SpringBootApplication
@EnableAutoConfiguration
////引入spring配置文件
@ImportResource(locations = {"classpath:config/conf.xml",//导入本项目配置
        "classpath*:config/base-conf.xml",//导入base项目的默认配置
        "classpath*:config/spring-db-setting.xml",//导入base项目的数据库配置
        "classpath*:config/spring-durid-bean.xml"})//导入base项目的durid项目配置
public class SimpleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SimpleApplication.class, args);
    }
}
