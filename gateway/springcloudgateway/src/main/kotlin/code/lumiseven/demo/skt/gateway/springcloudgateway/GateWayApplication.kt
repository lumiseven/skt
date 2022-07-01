package code.lumiseven.demo.skt.gateway.springcloudgateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class GateWayApplication

fun main(args: Array<String>) {
    runApplication<GateWayApplication>(*args)
}