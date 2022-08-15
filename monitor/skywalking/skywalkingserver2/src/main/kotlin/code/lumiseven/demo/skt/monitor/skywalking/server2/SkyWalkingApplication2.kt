package code.lumiseven.demo.skt.monitor.skywalking.server2

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class SkyWalkingApplication2

fun main(args: Array<String>) {
    runApplication<SkyWalkingApplication2>(*args)
}