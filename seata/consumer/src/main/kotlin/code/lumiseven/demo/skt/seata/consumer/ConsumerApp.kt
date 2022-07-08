package code.lumiseven.demo.skt.seata.consumer

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
@EnableDiscoveryClient
@EnableFeignClients // 扫描 @FeignClient 注解
@MapperScan("code.lumiseven.demo.skt.seata.consumer.mapper")
class ConsumerApp

fun main(args: Array<String>) {
    runApplication<ConsumerApp>(*args)
}