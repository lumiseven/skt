package code.lumiseven.demo.skt.gateway.springcloudgateway

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class FallbackController {
    @GetMapping("/fallback")
    fun fallback(): String = "Message from Spring Cloud Gateway fallback."
}