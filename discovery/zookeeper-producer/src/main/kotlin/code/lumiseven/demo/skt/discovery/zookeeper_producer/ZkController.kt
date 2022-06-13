package code.lumiseven.demo.skt.discovery.zookeeper_producer

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ZkController {
    @GetMapping("hello/zk")
    fun helloZk(): String {
        return "hello zookeeper"
    }
}