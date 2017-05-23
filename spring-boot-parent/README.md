redis 使用一主多从 哨兵模式

redis 哨兵配置
    
    #sentinel monitor <master-name> <ip> <redis-port> <quorum>
    port 27000
    #仅监听ipv4的网络接口
    bind 0.0.0.0
    sentinel monitor mymaster 127.0.0.1 6379 1
    sentinel down-after-milliseconds mymaster 30000
    sentinel failover-timeout mymaster 18000
    sentinel parallel-syncs mymaster 1 
