package code.lumiseven.demo.skt.seata.provider

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
@EnableDiscoveryClient
@MapperScan("code.lumiseven.demo.skt.seata.provider.mapper")
class ProviderApp

fun main(args: Array<String>) {
    runApplication<ProviderApp>(*args)
}