#spring-boot

集成mybatis和阿里的druid,redis,log4j2

增加权限判断用自定义标签和aop编程暂时没加入shiro


后期继续集成shiro 消息队列

spring-boot-mybatis 主要测试使用，运行用core

将所有的aop base方法啊参数全都放到java-base-program里面


redis 使用一主多从 哨兵模式 spring-boot-redis-sentinel

#修改配置方式
将所有的公用方法，配置放在java-base-program里面

在使用的时候spring-boot的application中加入

    @ImportResource(locations = {"classpath:config/conf.xml",//导入本项目配置
            "classpath*:config/base-conf.xml",//导入base项目的默认配置
            "classpath*:config/spring-db-setting.xml",//导入base项目的数据库配置
            "classpath*:config/spring-durid-bean.xml"})//导入base项目的durid项目配置


但某些需要配合application.properties的配置来修改不同项目之间的差异

##开启Druid监控sql和spring

    "classpath*:config/spring-db-setting.xml",//导入base项目的数据库配置

需要在application.properties中加入修改

    #Durid-spring durid监控spring的监控路径监控所有controller
    spring.durid.package=com.cache.redis.web.*

来修改druid监控的spring位置

##开启数据库缓存缓存到Redis中
xml引用：

    "classpath*:config/spring-cache-redis.xml",//导入base项目的cacheRedis配置
    
application.properties修改：
      
      ##############################################################
      #Cache Redis
      ###############################################################
      #缓存超时时间
      spring.cache.redis.maxtime=10000
      spring.cache.redis.packageList=com.cache.redis.domain
      ###############################################################