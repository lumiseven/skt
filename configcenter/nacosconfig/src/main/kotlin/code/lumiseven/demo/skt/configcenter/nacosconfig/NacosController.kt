package code.lumiseven.demo.skt.configcenter.nacosconfig

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class NacosController {
    @Autowired
    lateinit var nacosConfig: NacosConfig

    @Autowired
    lateinit var nacosConfig2: NacosConfig2

    @GetMapping("config")
    fun getNacosConfig(): String {
        return """${nacosConfig.env}
            |${nacosConfig.user}""".trimMargin()
    }

    @GetMapping("config2")
    fun getNacosConfig1(): String {
        return """${nacosConfig2.env}
            |${nacosConfig2.username}
            |${nacosConfig2.password}""".trimMargin()
    }
}