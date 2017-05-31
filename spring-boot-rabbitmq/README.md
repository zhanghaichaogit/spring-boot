简单的消息队列配置

需要去掉数据库的pom配置

或者在application.properties加入：

    spring.datasource.type=${pom.datasource.type}
    spring.datasource.url=${pom.jdbc.host.url}
    spring.datasource.username=${pom.jdbc.host.username}
    spring.datasource.password=${pom.jdbc.host.password}
    # 驱动
    spring.datasource.driver-class-name=${pom.datasource.driver-class-name}