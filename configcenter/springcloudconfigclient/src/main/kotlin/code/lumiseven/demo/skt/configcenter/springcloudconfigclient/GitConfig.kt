package code.lumiseven.demo.skt.configcenter.springcloudconfigclient

import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.stereotype.Component

@Component
@RefreshScope
data class GitConfig(
    @Value("\${key123")
    val key123: String,
//    @Value("\${key456}")
//    val key456: String,
//    @Value("\${data.username}")
//    val username: String,
//    @Value("\${from}")
//    val from: String,
)