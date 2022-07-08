package code.lumiseven.demo.skt.seata.provider.mapper

import code.lumiseven.demo.skt.seata.provider.domain.TbUser

interface TbUserMapper {
    fun insert(record: TbUser): Int
}