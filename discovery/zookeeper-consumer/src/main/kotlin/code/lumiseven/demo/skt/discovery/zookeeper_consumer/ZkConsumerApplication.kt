package code.lumiseven.demo.skt.discovery.zookeeper_consumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
class ZkConsumerApplication {
    @Bean
    @LoadBalanced
    fun restTemplate():RestTemplate {
        return RestTemplate()
    }
}

fun main(args: Array<String>) {
    runApplication<ZkConsumerApplication>(*args)
}