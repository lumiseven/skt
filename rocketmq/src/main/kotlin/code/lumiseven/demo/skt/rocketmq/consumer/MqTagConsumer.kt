package code.lumiseven.demo.skt.rocketmq.consumer

import code.lumiseven.demo.skt.rocketmq.entity.OrderPaidEvent
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener
import org.apache.rocketmq.spring.core.RocketMQListener
import org.springframework.stereotype.Component

@Component
@RocketMQMessageListener(topic = "test-topic", consumerGroup = "test-consumer-2", selectorExpression = "test-tag")
class MqTagConsumer: RocketMQListener<OrderPaidEvent> {

    override fun onMessage(p0: OrderPaidEvent?) {
        println("MqTagConsumer received: $p0")
    }

}