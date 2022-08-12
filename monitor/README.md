## springcloud monitor
`springboot3.0` 的 [可观测性](https://spring.io/blog/2022/10/12/observability-with-spring-boot-3)

### 监控方案
- prometheus + grafana
- zipkin
- skywalking

### prometheus + grafana 搭建
[docker-compose](prometheus/docker/docker-compose.yml)

`grafana` 中可以使用 [micrometer](https://grafana.com/grafana/dashboards/4701-jvm-micrometer/) 模板

### zipkin
1. 服务安装
    ```shell
    # Note: this is mirrored as ghcr.io/openzipkin/zipkin
    docker run -d -p 9411:9411 openzipkin/zipkin
    ```
2. 启动服务注册中心 `zookeeper`
