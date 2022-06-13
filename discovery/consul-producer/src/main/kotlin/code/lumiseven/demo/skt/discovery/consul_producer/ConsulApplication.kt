package code.lumiseven.demo.skt.discovery.consul_producer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class ConsulApplication

fun main(args: Array<String>) {
    runApplication<ConsulApplication>(*args)
}

