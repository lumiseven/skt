## springcloud + gateway

### zuul(不推荐在新版本的 springboot/springcloud 中集成)
**不推荐在新版本的 springboot/springcloud 中集成 zuul**
[zuul pom](./zuul/pom.xml) 使用不同版本 `spring-boot-start` 的 `parent` 构建

测试 zuul 作为微服务网关
由于微服务网关较为依赖`服务发现`以及`动态路由配置` 所以在测试网关的同时需要启动 `注册中心` `配置中心` 并启动`资源服务`以供调用
本次测试zuul的相关中间件:
- 注册中心 + 配置中心: consul(consulconfig [配置参考](../configcenter/consulconfig/pom.xml))
- 资源服务: [consul-producer](../discovery/consul-producer) [consul-consumer](../discovery/consul-consumer)

启动步骤
1. 启动 consul
2. 启动 consul-config
3. 启动 zuul 和 consul-producer 和 consul-consumer
