package code.lumiseven.demo.skt.discovery.nacos_consumer

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(value = "nacos-producer", path = "/")
@Component
interface FeignService {
    @GetMapping("hello/nacos")
    fun helloNacos(): String
}