package code.lumiseven.demo.skt.seata.consumer.feign

import lombok.Data
import java.io.Serializable

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(value = "seata-provider")
interface ProviderFeignClient {
    @PostMapping("/add/user")
    fun add(@RequestBody user: TbUser): TbUser
}

@Data
class TbUser : Serializable {
    var id: Int? = null
    var name: String? = null
    var age: Int? = null
}