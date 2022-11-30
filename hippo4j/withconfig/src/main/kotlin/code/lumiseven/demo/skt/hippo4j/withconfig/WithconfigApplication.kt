package code.lumiseven.demo.skt.hippo4j.withconfig

import cn.hippo4j.core.enable.EnableDynamicThreadPool
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableDynamicThreadPool
class WithconfigApplication

fun main(args: Array<String>) {
    runApplication<WithconfigApplication>(*args)
}