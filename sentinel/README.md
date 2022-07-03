## springcloud sentinel

### sentinel
依赖 `consul` 存放配置文件

### sentinel-dashboard
本地测试部署:
```shell
docker run --name sentinel  -d -p 8858:8858 -d  bladex/sentinel-dashboard:1.7.2
```