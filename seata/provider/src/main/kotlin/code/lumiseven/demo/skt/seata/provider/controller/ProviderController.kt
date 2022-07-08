package code.lumiseven.demo.skt.seata.provider.controller

import code.lumiseven.demo.skt.seata.provider.domain.TbUser
import code.lumiseven.demo.skt.seata.provider.mapper.TbUserMapper
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.beans.factory.annotation.Autowired

@RestController
class ProviderController {
    @Autowired
    lateinit var userMapper: TbUserMapper

    @PostMapping("/add/user")
    fun add(@RequestBody user: TbUser): TbUser {
        println("add user: $user")
        user.name = "provider"
        userMapper.insert(user)
        return user
    }
}