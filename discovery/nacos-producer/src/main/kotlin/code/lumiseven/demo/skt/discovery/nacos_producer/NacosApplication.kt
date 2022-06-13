package code.lumiseven.demo.skt.discovery.nacos_producer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class NacosApplication

fun main(args: Array<String>) {
    runApplication<NacosApplication>(*args)
}