package code.lumiseven.demo.skt.discovery.eureka_producer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@EnableEurekaClient
@SpringBootApplication
class ProducerApplication

fun main(args: Array<String>) {
    runApplication<ProducerApplication>(*args)
}