package code.lumiseven.demo.skt.hippo4j.withconfig

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.ThreadPoolExecutor
import javax.annotation.Resource

@RestController
class ThreadPoolTestController {

    @Resource
    private lateinit var messageConsumeDynamicExecutor: ThreadPoolExecutor

    @Resource
    private lateinit var messageProduceDynamicExecutor: ThreadPoolExecutor

    @GetMapping("execute1")
    fun getNacosConfig(): String {
        messageConsumeDynamicExecutor.execute {
            println("messageConsumeDynamicExecutor.execute")
            Thread.sleep(10000)
        }
        messageProduceDynamicExecutor.execute {
            println("messageProduceDynamicExecutor.execute")
            Thread.sleep(10000)
        }
        return "execute1 finished"
    }

}