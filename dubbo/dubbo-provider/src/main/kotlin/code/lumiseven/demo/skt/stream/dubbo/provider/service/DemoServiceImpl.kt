package code.lumiseven.demo.skt.stream.dubbo.provider.service

import code.lumiseven.demo.skt.stream.dubbo.`interface`.DemoService
import org.apache.dubbo.config.annotation.DubboService

@DubboService
class DemoServiceImpl: DemoService {
    override fun sayHello(name: String): String {
        return "Hello $name".also(::println)
    }
}