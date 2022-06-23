package code.lumiseven.demo.skt.configcenter.apolloconfig

import com.ctrip.framework.apollo.model.ConfigChangeEvent
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "data")
class ApolloConfig {
    var env: String? = null
    var user: User? = null

    @ApolloConfigChangeListener
    fun configChangeHandlerUserName(configChangeEvent: ConfigChangeEvent) {
        if(configChangeEvent.isChanged("data.user.username")) {
            user?.username = configChangeEvent.getChange("data.user.username").newValue
            println("${user?.username} is change")
        }
    }
}

data class User(
    var username: String?,
    var password: String?
) {
    // 注意需要有一个无参数构造方法
    constructor(): this(null, null)
}