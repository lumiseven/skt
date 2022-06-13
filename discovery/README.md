## springcloud 服务发现 + 微服务调用

### 服务发现
- eureka
- consul
- zookeeper
- nacos

### 微服务间调用
- openfeign

### 步骤
每一种注册中心的实现 都会有
1. 单机部署 启动 部分没有代码而是部署命令
2. 注册到 注册中心的两个服务 producer 和 consumer
3. consumer 会使用 openFeign 调用producer的服务测试

### 单机 启动 consul 测试
```shell
docker run -d -p 8500:8500 -p 8300:8300 -p 8301:8301 -p 8302:8302 -p 8600:8600/udp consul:1.13 consul agent -dev -client=0.0.0.0
```

### 单机 启动 zookeeper 测试
```shell
docker run -d  -p 2181:2181 --name myzookeeper --restart always zookeeper:3.7
```

### 单机 启动 nacos 测试
```shell
docker run -d -p 8849:8848 --name nacos-quick -e MODE=standalone -d nacos/nacos-server:v2.1.0

## 访问
http://localhost:8849/nacos
username: nacos 
password: nacos
```

报错说明:
> NoClassDefFoundError: org/springframework/boot/context/properties/ConfigurationBeanFactoryMetadata

- springboot 2.4 之后移除了 ConfigurationBeanFactoryMetadata
- 注意项目中 nacos 版本 `2.2.7.RELEASE` 并不兼容

注意 nacos 与 springcloud版本
```xml
<properties>
    <springcloud.nacos.version>2021.0.1.0</springcloud.nacos.version>
    <springcloud.loadbalancer.version>3.1.1</springcloud.loadbalancer.version>
    <springcloud.openfeign.version>3.1.1</springcloud.openfeign.version>
</properties>
```
