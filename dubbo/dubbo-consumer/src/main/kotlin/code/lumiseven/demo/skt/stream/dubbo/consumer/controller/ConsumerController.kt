package code.lumiseven.demo.skt.stream.dubbo.consumer.controller

import code.lumiseven.demo.skt.stream.dubbo.`interface`.DemoService
import org.apache.dubbo.config.annotation.Reference
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ConsumerController {
    @Reference
    lateinit var demoService: DemoService

    @GetMapping("/dubbo/echo/{name}")
    fun dubboEcho(@PathVariable("name") name: String): String {
        return demoService.sayHello(name)
    }
}