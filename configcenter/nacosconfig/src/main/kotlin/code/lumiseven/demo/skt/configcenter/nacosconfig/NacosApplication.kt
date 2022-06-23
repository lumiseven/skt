package code.lumiseven.demo.skt.configcenter.nacosconfig

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NacosApplication

fun main(args: Array<String>) {
    runApplication<NacosApplication>(*args)
}