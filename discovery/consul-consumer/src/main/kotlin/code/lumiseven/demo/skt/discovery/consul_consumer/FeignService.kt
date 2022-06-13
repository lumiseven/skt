package code.lumiseven.demo.skt.discovery.consul_consumer

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(value = "consul-producer")
interface FeignService {
    @GetMapping("hello/consul")
    fun helloConsul(): String
}