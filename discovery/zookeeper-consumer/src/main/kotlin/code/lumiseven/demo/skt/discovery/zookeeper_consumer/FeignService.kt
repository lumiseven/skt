package code.lumiseven.demo.skt.discovery.zookeeper_consumer

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(value = "zk-producer", path = "/")
@Component
interface FeignService {
    @GetMapping("hello/zk")
    fun helloZk(): String
}