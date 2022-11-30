## springboot hippo4j 
动态线程池管理 [hippo4j](https://hippo4j.cn/docs/user_docs/intro)

运行模式:
1. `hippo4j-config` 轻量级依赖配置中心
2. `hippo4j-server` 无中间件依赖

### `hippo4j-config` 轻量级依赖配置中心 模式配置
[文档](https://hippo4j.cn/docs/user_docs/getting_started/config/hippo4j-config-start)

1. nacos
选用 `nacos` 作为配置中心 配置文件放入 [config](withconfig/config)
`nacos` [部署参考](../configcenter/nacosconfig/pom.xml)

2. prometheus + grafana

   [文档](https://hippo4j.cn/docs/user_docs/getting_started/config/hippo4j-config-monitor#%E9%85%8D%E7%BD%AE-prometheus)

prometheus:
   - setup
      ```shell
      docker run -d -p 9090:9090 --add-host "host.docker.internal:host-gateway" --name prometheus prom/prometheus
      # 进入 prometheus 容器内部
      docker exec -it prometheus /bin/sh
      # 编辑 prometheus 配置文件
      vi /etc/prometheus/prometheus.yml
      ```

   - scrape_configs 节点下新添加一个 job，如果 Prometheus 是 Docker 方式部署，{scrape_configs.static_configs.targets} 需要写本机的 IP。
   
   - 修改配置
      ```yaml
      scrape_configs:
        - job_name: 'dynamic-thread-pool-job'
          scrape_interval: 5s
          metrics_path: '/actuator/prometheus'
          static_configs:
            - targets: [ 'host.docker.internal:9096' ]
      ```

grafana:
   - setup
      ```shell
      docker run -d -p 3000:3000 --add-host "host.docker.internal:host-gateway" --name=grafana grafana/grafana
      ```
   - 添加 prometheus 数据源
   - import [json](withconfig/src/main/resources/20220909_hippo4j-grafana.json)
   
### `hippo4j-server` 无中间件依赖
`hippo4j-server` [docker 部署](https://hippo4j.cn/docs/user_docs/ops/server-docker)

- setup
    ```shell
    docker run -d -p 6691:6691 --name hippo4j-server hippo4j/hippo4j-server
    ```

- 或者，底层存储数据库切换为 MySQL。DATASOURCE_HOST 需要切换为本地 IP，不能使用 127.0.0.1 或 localhost。
    - 1. import sql [hippo4j_manager.sql](https://github.com/opengoofy/hippo4j/blob/develop/hippo4j-server/hippo4j-bootstrap/src/main/resources/sql-script/mysql/hippo4j_manager.sql) [local_hippo4j_manager.sql](noconfig/src/main/resources/hippo4j_manager.sql)
    ```shell
    docker run -d -p 6691:6691 --name hippo4j-server \
    --add-host "host.docker.internal:host-gateway" \
    -e DATASOURCE_MODE=mysql \
    -e DATASOURCE_HOST=host.docker.internal \
    -e DATASOURCE_PORT=3306 \
    -e DATASOURCE_DB=hippo4j_manager \
    -e DATASOURCE_USERNAME=root \
    -e DATASOURCE_PASSWORD=admin \
    hippo4j/hippo4j-server
    ```
- 访问 Server 控制台，路径 `http://localhost:6691/index.html` ，默认用户名密码：`admin` / `123456`
