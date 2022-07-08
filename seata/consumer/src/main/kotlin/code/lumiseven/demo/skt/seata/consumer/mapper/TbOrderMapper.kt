package code.lumiseven.demo.skt.seata.consumer.mapper

import code.lumiseven.demo.skt.seata.consumer.domain.TbOrder

interface TbOrderMapper {
    fun insert(record: TbOrder): Int
}