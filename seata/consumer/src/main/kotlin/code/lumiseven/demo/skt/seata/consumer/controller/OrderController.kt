package code.lumiseven.demo.skt.seata.consumer.controller

import code.lumiseven.demo.skt.seata.consumer.domain.TbOrder
import code.lumiseven.demo.skt.seata.consumer.feign.ProviderFeignClient
import code.lumiseven.demo.skt.seata.consumer.feign.TbUser
import code.lumiseven.demo.skt.seata.consumer.mapper.TbOrderMapper
import io.seata.core.context.RootContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestBody
import io.seata.spring.annotation.GlobalTransactional
import org.springframework.web.bind.annotation.PostMapping

@RestController
class OrderController {
    @Autowired
    lateinit var orderMapper: TbOrderMapper

    @Autowired
    lateinit var providerFeignClient: ProviderFeignClient

    @PostMapping("/seata/userAndOrder/add")
    @GlobalTransactional(rollbackFor = [Exception::class]) // 开启全局事务
    fun add(@RequestBody m: Map<String, String>): String {
        println("globalTransactional begin, Xid: ${RootContext.getXID()}")

        val name = m.getOrDefault("name", "")
        val age = m.getOrDefault("age", "0").toInt()
        val description = m.getOrDefault("description", "")

        // call provider save
        val tbUser = TbUser()
        tbUser.name = name
        tbUser.age = age
        val resultUser = providerFeignClient.add(tbUser)

        // local save
        val tbOrder = TbOrder()
        tbOrder.userId = resultUser.id
        tbOrder.description = description
        orderMapper.insert(tbOrder)

        // test seata globalTransactional
        throw RuntimeException("抛出异常 事务回滚")
//        return "success"
    }

}