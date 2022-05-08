package code.lumiseven.demo.skt.rocketmq.producer

import code.lumiseven.demo.skt.rocketmq.entity.OrderPaidEvent
import org.apache.rocketmq.client.producer.SendCallback
import org.apache.rocketmq.client.producer.SendResult
import org.apache.rocketmq.spring.core.RocketMQTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component
import java.lang.Exception

@Component
class MqProducer {

    @Autowired
    private lateinit var rocketMQTemplate: RocketMQTemplate
    
    // 同步发送信息到 test-topic
    fun sendMessage(orderPaidEvent: OrderPaidEvent) {
        println("send message: $orderPaidEvent")
        rocketMQTemplate.send("test-topic", MessageBuilder.withPayload(orderPaidEvent).build())
    }

    // 同步发送信息到 test-topic 并携带tag test-tag
    fun sendMessageWithTag(orderPaidEvent: OrderPaidEvent) {
        println("send message: $orderPaidEvent, tag: test-tag")
        rocketMQTemplate.convertAndSend("test-topic:test-tag", orderPaidEvent)// topic:tag
    }

    // 同步发送信息到 test-topic
    fun convertAndSendMessage(orderPaidEvent: OrderPaidEvent) {
        println("convertAndSend message: $orderPaidEvent")
        rocketMQTemplate.convertAndSend("test-topic", orderPaidEvent)
    }

    // 异步发送信息到 test-topic 并设置超时时间 1s
    fun asyncSendMessage(orderPaidEvent: OrderPaidEvent) {
        println("async send single message: $orderPaidEvent")
        rocketMQTemplate.asyncSend("test-topic", orderPaidEvent, object: SendCallback{
            override fun onSuccess(p0: SendResult?) {
                println("async send success: $p0")
            }

            override fun onException(p0: Throwable?) {
                throw Exception(p0)
            }

        }, 1000L)
    }

    // 同步批量发送信息到 test-topic 并设置超时时间 60s
    fun syncSendBatchMessage(orderPaidEvents: List<OrderPaidEvent>) {
        println("sync send batch message: $orderPaidEvents")
        val msgList = orderPaidEvents.map { MessageBuilder.withPayload(it).build() }
        rocketMQTemplate.syncSend("test-topic", msgList, 60000L)
    }

    // 同步发送信息到 test-topic
    fun syncSendMessage(orderPaidEvent: OrderPaidEvent) {
        println("sync send single message: $orderPaidEvent")
        rocketMQTemplate.syncSend("test-topic", orderPaidEvent)
    }

}