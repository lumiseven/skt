## springcloud 配置中心

### 包含实现
- springcloud-config

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
