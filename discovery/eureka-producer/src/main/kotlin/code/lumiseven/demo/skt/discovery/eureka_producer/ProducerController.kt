package code.lumiseven.demo.skt.discovery.eureka_producer

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProducerController {

    @GetMapping("/provide")
    fun provide(): String {
        return "Hello From Provider"
    }

    @GetMapping("/limit")
    fun limit(): String {
        return "Limit Interface"
    }
}