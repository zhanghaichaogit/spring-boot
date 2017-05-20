package com.pro.base.monitor;

import com.alibaba.druid.support.json.JSONUtils;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Created by zhanghaichao on 2017/5/16.
 */

/**
 * 监控数据库的操作
 * 有了druid后这这个就用不到了
 */

@Intercepts(value = {
        @Signature(type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class,
                        CacheKey.class, BoundSql.class}),
        @Signature(type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class MybatisMonitor implements Interceptor {

    private final static Logger LOGGER = LoggerFactory.getLogger(MybatisMonitor.class);

    /**
     * 实现拦截的地方
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];

        Object result = null;
        //请求参数
        Object parameter = null;
        if (1 < invocation.getArgs().length) {
            parameter = invocation.getArgs()[1];
        }
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        String sql = boundSql.getSql();
        //执行的sql所在的mapper文件
        String resource = mappedStatement.getResource();
        //执行sql的dao文件的包名+方法名
        String daoMethod = mappedStatement.getId();
        //去除sql文中的换行
        sql = sql.replace("\n", "").replaceAll("\\s+", " ");

        long start = System.currentTimeMillis();
        long end = System.currentTimeMillis();
        LOGGER.info("[MybatisMonitor]SQL监控：{}|{}，执行SQL：{}，参数：{},执行时间", resource, daoMethod,
                sql, JSONUtils.toJSONString(parameter));
        result = invocation.proceed();
        return result;
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
