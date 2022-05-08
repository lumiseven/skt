## springboot rocketmq

### 使用 docker-compose 在本地搭建一个 rocketmq 用于测试

1. 部署步骤可见: https://cloud.tencent.com/developer/article/1621263

2. 修改配置:
    - broker.conf 中的 brokerIp1 属性修改为本机的ip

3. 启动
   ```shell
   # 进入目录后执行
   docker-compose up
   ```

4. 访问
   - 本机 web 访问 `http://localhost:8080/` 
   - spring-boot 访问