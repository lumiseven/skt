package code.lumiseven.demo.skt.monitor.skywalking.server2

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SkyWalkingController {

    @GetMapping("service2")
    fun service2(): String {
        return "Hello, I'm SkyWalking"
    }
}