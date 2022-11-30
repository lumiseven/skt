package code.lumiseven.demo.skt.hippo4j.withconfig

import cn.hippo4j.core.executor.DynamicThreadPool
import cn.hippo4j.core.executor.support.ThreadPoolBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.ThreadPoolExecutor

@Configuration
class ThreadPoolConfig {

    @Bean
    @DynamicThreadPool
    fun messageConsumeDynamicExecutor(): ThreadPoolExecutor? {
        val threadPoolId = "message-consume"
        return ThreadPoolBuilder.builder()
            .threadFactory(threadPoolId)
            .threadPoolId(threadPoolId)
            .dynamicPool()
            .build()
    }

    @Bean
    @DynamicThreadPool
    fun messageProduceDynamicExecutor(): ThreadPoolExecutor? {
        val threadPoolId = "message-produce"
        return ThreadPoolBuilder.builder()
            .threadFactory(threadPoolId)
            .threadPoolId(threadPoolId)
            .dynamicPool()
            .build()
    }

}