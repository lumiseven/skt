## springboot-elasticsearch
`Note: es version: 7.17 使用 es 版本 8.0+ 与 springboot-es 支持不好`

### 使用 docker 在本地启动 elasticsearch 用于测试
```shell
docker network create elastic
docker run --memory="4g" --name es01 --net elastic -e "discovery.type=single-node" -p 9200:9200 -p 9300:9300 -d -it elasticsearch:7.17.1
```

### 安装 ik 分词器用于字段分词处理
```shell
# ik version 8.1.0 为例
./bin/elasticsearch-plugin install https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v7.17.1/elasticsearch-analysis-ik-7.17.1.zip

## 离线安装

#./bin/elasticsearch-plugin install file:///usr/share/elasticsearch/elasticsearch-analysis-ik-7.17.1.zip
```
