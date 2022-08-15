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
1. 服务安装(docker)
    ```shell
    # Note: this is mirrored as ghcr.io/openzipkin/zipkin
    docker run -d -p 9411:9411 openzipkin/zipkin
    ```
2. 启动服务注册中心 `zookeeper`
3. 访问服务 查看 `zipkin` 控制台 `http://localhost:9411`

### skywalking
1. 服务安装(docker-compose)
   使用官方的 `docker-compose` 安装 [code](https://github.com/apache/skywalking/tree/master/docker)
   [docker-compose](skywalking/docker/docker-compose.yml)
   `ui username:admin, password:888888`
2. 启动服务注册中心 `zookeeper`
3. 使用 `java-agent` 方式接入 [文档](https://skywalking.apache.org/docs/skywalking-java/v8.13.0/en/setup/service-agent/java-agent/readme/)
   1. [下载](https://skywalking.apache.org/downloads/) `java-agent` 并安装
   2. 配置 `skywalking-agent/config/agent.config`
   3. 运行 `SW_AGENT_NAME=swserver1 java -javaagent:/path/to/skywalking-agent/skywalking-agent.jar -jar yourApp.jar` 
   4. 注意运行时添加env `SW_AGENT_NAME` 用以区分服务名称 *详细配置参考配置文件 agent.config*
