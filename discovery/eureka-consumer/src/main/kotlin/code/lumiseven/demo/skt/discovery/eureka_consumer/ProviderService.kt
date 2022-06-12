package code.lumiseven.demo.skt.discovery.eureka_consumer

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(value = "provider-server")
interface ProviderService {
    @GetMapping("/provide")
    fun provide(): String
}