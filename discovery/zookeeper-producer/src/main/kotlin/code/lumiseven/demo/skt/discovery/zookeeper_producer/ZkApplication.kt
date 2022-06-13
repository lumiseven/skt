package code.lumiseven.demo.skt.discovery.zookeeper_producer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class ZkApplication

fun main(args: Array<String>) {
    runApplication<ZkApplication>(*args)
}