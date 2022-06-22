### springcloud-config 配置中心 服务端

配置文件的真实存放路径就为本项目的git中 `configcenter/src/main/resources/config/`

注意要先提交配置修改后再测试

具体参数见: [application.yml](./src/main/resources/application.yml)
```yml
search-paths:
- configcenter/src/main/resources/config/{application}
- configcenter/src/main/resources/config/default
```
