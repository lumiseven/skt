package code.lumiseven.demo.skt.discovery.consul_producer

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.CommonsRequestLoggingFilter


@Configuration
class LoggingConfig {
    @Bean
    fun requestLoggingFilter(): CommonsRequestLoggingFilter {
        return CommonsRequestLoggingFilter().apply {
            this.setIncludeHeaders(true)
            this.setIncludeClientInfo(true)
            this.setIncludeQueryString(true)
            this.setIncludePayload(true)
            this.setMaxPayloadLength(32768)
        }
    }
}
