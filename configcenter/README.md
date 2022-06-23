## springcloud 配置中心

### 包含实现
- springcloud-config
- [apollo](https://github.com/apolloconfig/apollo)

### springcloud-config client
报错: The spring.config.import property is missing a configserver: entry
https://stackoverflow.com/a/70813003/5751473

注意 springcloud-config 的配置文件位置配置
严格参照文档说明: https://docs.spring.io/spring-cloud-config/docs/current/reference/html/

```txt
/{application}/{profile}[/{label}]
/{application}-{profile}.yml
/{label}/{application}-{profile}.yml
/{application}-{profile}.properties
/{label}/{application}-{profile}.properties
```

### apollo 单击部署 (使用官方 [docker-quick-start](https://www.apolloconfig.com/#/zh/deployment/quick-start-docker))
[docker-compose](apolloconfig/docker-quick-start/docker-compose.yml)

1. apollo新增 project `apolloconfigclient`
2. 在默认 namespace `application` 新增 `configuration`
    ```properties
    data.env=test
    data.user.username=ls
    data.user.password=123456
    ```

### nacos
1. 在默认 namespace `public` 默认 group `DEFAULT_GROUP` 新增
   - nacos-config-client.yml
   ```yml
   data:
     env: test
   user:
     username: ls-nacos
     password: 123456
   data2:
     env: test-data2
     username: ls-nacos-data2
     password: 654321
   ```
   - nacos-config-client-dev.yml
   ```yml
   data:
     env: dev
   user:
     username: ls-nacos
     password: 123456
   data2:
     env: dev-data2
     username: ls-nacos-data2
     password: 654321
   ```
