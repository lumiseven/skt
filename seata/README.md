## springboot alibaba-seata

### 测试环境安装启动
```shell
### 本地 docker 启动 seata AT
docker run -d --name seata-server -p 8091:8091 seataio/seata-server:1.5.2
```

### 模拟分布式事物
依赖环境:
- seata
- consul(注册中心)
- mysql

此外 由于 seata 中依赖了 alibaba-druid 使用 druid作为数据库连接管理工具

### [配置](http://seata.io/zh-cn/docs/user/configurations.html)

#### provider
操作mysql 新增数据

#### consumer
rpc调用 provider 操作mysql 新增数据 抛出异常
