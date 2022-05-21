package code.lumiseven.demo.skt.es.repository

import code.lumiseven.demo.skt.es.entity.Item
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

interface ItemRepository: ElasticsearchRepository<Item, Long>