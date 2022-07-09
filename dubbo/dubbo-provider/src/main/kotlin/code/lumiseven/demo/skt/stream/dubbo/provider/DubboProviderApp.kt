package code.lumiseven.demo.skt.stream.dubbo.provider

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
@EnableDubbo
class DubboProviderApp

fun main(args: Array<String>) {
    runApplication<DubboProviderApp>(*args)
}