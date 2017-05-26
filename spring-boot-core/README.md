##抽离出公共数据库操作方法放在java-base-program中

##集成redis durid mybatis

将durid的配置方法放在java-base-program config中

其他项目调用时配置bean
        
    <bean id="druidConfiguration"
              class="com.pro.base.config.DruidConfiguration"/>
                  
并在application.properties中加入
            
    ###############################################################
    #Durid-spring durid监控spring的监控路径监控所有controller
    spring.durid.package=com.base.core.web.*
    ###############################################################
    
配置mybatis 的mapper扫描位置就可以了    