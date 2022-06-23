package code.lumiseven.demo.skt.configcenter.nacosconfig

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class NacosConfig2 {
    @Value(value = "\${data2.env:dev}")
    var env: String? = null
    @Value(value = "\${data2.username}")
    var username: String? = null
    @Value(value = "\${data2.password}")
    var password: String? = null
}