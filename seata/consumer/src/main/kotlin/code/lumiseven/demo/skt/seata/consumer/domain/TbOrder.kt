package code.lumiseven.demo.skt.seata.consumer.domain

import lombok.Data
import java.io.Serializable

@Data
class TbOrder: Serializable {
    var id: Int? = null
    var userId: Int? = null
    var description: String? = null
}