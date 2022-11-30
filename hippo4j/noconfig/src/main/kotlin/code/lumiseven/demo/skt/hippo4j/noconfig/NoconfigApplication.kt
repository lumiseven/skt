package code.lumiseven.demo.skt.hippo4j.noconfig

import cn.hippo4j.core.enable.EnableDynamicThreadPool
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableDynamicThreadPool
class NoconfigApplication

fun main(args: Array<String>) {
    runApplication<NoconfigApplication>(*args)
}