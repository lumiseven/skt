package code.lumiseven.demo.skt.es.entity

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType

@Document(indexName = "item", shards = 1, replicas = 0)
data class Item(
    @Id
    val id: Long,

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    val title: String,

    @Field(type = FieldType.Keyword)
    val category: String,

    @Field(type = FieldType.Keyword)
    val brand: String,

    @Field(type = FieldType.Double)
    val price: Double,

    @Field(index = false, type = FieldType.Keyword)
    val images: String
) {
    constructor(): this(0L, "", "", "", 0.0, "")
}