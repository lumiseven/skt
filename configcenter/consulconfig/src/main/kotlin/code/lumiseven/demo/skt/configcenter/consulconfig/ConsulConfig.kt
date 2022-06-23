package code.lumiseven.demo.skt.configcenter.consulconfig

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "data")
class ConsulConfig {
    var env: String? = null
    var user: User? = null
}

data class User(
    var username: String?,
    var password: String?
) {
    constructor(): this(null, null)
}