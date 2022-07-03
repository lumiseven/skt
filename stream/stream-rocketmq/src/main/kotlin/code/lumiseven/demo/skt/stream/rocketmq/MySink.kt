package code.lumiseven.demo.skt.stream.rocketmq

import org.springframework.cloud.stream.annotation.Input
import org.springframework.messaging.SubscribableChannel

interface MySink {
    @Input("input1")
    fun input1(): SubscribableChannel

    @Input("input2")
    fun input2(): SubscribableChannel
}