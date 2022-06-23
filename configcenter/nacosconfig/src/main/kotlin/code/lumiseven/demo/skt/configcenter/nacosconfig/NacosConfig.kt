package code.lumiseven.demo.skt.configcenter.nacosconfig

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "data")
class NacosConfig {
    var env: String? = null
    var user: User? = null
}

data class User(
    var username: String?,
    var password: String?
) {
    // 注意需要有一个无参数构造方法
    constructor(): this(null, null)
}