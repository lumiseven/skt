package code.lumiseven.demo.skt.rocketmq.entity

import java.math.BigDecimal

class OrderPaidEvent(
    val orderId: String,
    val paidMoney: BigDecimal
) {
    constructor(): this("0", BigDecimal(0.0))

    override fun toString(): String {
        return "orderId: $orderId, paidMoney: $paidMoney"
    }
}