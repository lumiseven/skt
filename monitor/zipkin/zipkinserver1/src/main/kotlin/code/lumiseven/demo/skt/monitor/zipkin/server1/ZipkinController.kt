package code.lumiseven.demo.skt.monitor.zipkin.server1

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@RestController
class ZipkinController {
    @Autowired
    lateinit var restTemplate: RestTemplate

    @GetMapping("service1")
    fun service1(): String {
        return restTemplate.getForObject("http://zipkin-service2/service2", String::class)
    }
}