package code.lumiseven.demo.skt.discovery.nacos_producer

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class NacosController {
    @GetMapping("hello/nacos")
    fun helloNacos(): String {
        return "Hello Nacos"
    }
}