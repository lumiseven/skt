package code.lumiseven.demo.skt.es

import code.lumiseven.demo.skt.es.entity.Item
import code.lumiseven.demo.skt.es.repository.ItemRepository
import org.elasticsearch.index.query.QueryBuilders
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder
import org.springframework.test.context.junit.jupiter.SpringExtension

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@ExtendWith(SpringExtension::class)
class ItemRepositoryTest {

    @Autowired
    private lateinit var elasticsearchOperations: ElasticsearchOperations

    @Autowired
    private lateinit var itemRepository: ItemRepository

    @Test
    @Order(1)
    fun testCreateIndex() {
        elasticsearchOperations.indexOps(Item::class.java).apply {
            this.delete()
            this.create()
            this.putMapping()
        }
    }

    @Test
    @Order(2)
    fun testAddNewItem() {
        val item = Item(1L, "Iphone 11", "手机", "苹果", 8000.0, "http://image.baidu.com/13123.jpg")
        itemRepository.save(item)
    }

    @Test
    @Order(3)
    fun testBatchAddNewItems(){
        val items = arrayOf(
            Item(2L, "坚果手机R1", " 手机", "锤子", 3699.00, "http://image.baidu.com/13123.jpg"),
            Item(3L, "华为META10", " 手机", "华为", 4499.00, "http://image.baidu.com/13123.jpg")
        )
        itemRepository.saveAll(items.asList())
    }

    @Test
    @Order(4)
    fun testUpdateItem(){
        val item = Item(1L, "苹果XSMax", " 手机", "小米", 3499.00, "http://image.baidu.com/13123.jpg")
        itemRepository.save(item)
    }

    @Test
    @Order(5)
    fun testFindAll(){
        val items = itemRepository.findAll()
        assertEquals(3, items.toList().size)
    }

    @Test
    @Order(6)
    fun testFindByPage(){
        val items = itemRepository.findAll(PageRequest.of(0, 2))
        items.forEach { println(it) }
    }

    @Test
    @Order(7)
    fun testFindBySort(){
        val items = itemRepository.findAll(Sort.by("price").descending())
        items.forEach { println(it) }
    }

    @Test
    @Order(8)
    fun testMatchQuery(){
        val queryBuilder = NativeSearchQueryBuilder()
        queryBuilder.withQuery(QueryBuilders.matchQuery("title", "坚果手机"))
        val searchHits = elasticsearchOperations.search(queryBuilder.build(), Item::class.java)
        assertEquals(1, searchHits.totalHits)
        searchHits.searchHits.forEach {
            assertEquals(it.content.title, "坚果手机R1")
        }
        searchHits.searchHits.forEach(::println)
    }

    @Test
    @Order(9)
    fun testTermQuery(){
        val queryBuilder = NativeSearchQueryBuilder()
        queryBuilder.withQuery(QueryBuilders.termQuery("title", "坚果"))
        val searchHits = elasticsearchOperations.search(queryBuilder.build(), Item::class.java)
        assertEquals(1, searchHits.totalHits)
        searchHits.forEach(::println)
    }

    @Test
    @Order(10)
    fun testFuzzyQuery(){
        val queryBuilder = NativeSearchQueryBuilder()
        queryBuilder.withQuery(QueryBuilders.fuzzyQuery("title", "坚果"))
        val searchHits = elasticsearchOperations.search(queryBuilder.build(), Item::class.java)
        assertEquals(1, searchHits.totalHits)
        searchHits.forEach(::println)
    }

    @Test
    @Order(11)
    fun testBooleanQuery(){
        val queryBuilder = NativeSearchQueryBuilder()
        queryBuilder.withQuery(QueryBuilders.boolQuery()
            .must(QueryBuilders.termQuery("title", "坚果"))
            .must(QueryBuilders.termQuery("brand", "锤子")))

        val searchHits = elasticsearchOperations.search(queryBuilder.build(), Item::class.java)
        assertEquals(1, searchHits.totalHits)
        searchHits.forEach(::println)
    }

    @Test
    @Order(12)
    fun testRangeQuery(){
        val queryBuilder = NativeSearchQueryBuilder()
        queryBuilder.withQuery(QueryBuilders.rangeQuery("price").from(3000).to(4000))

        val searchHits = elasticsearchOperations.search(queryBuilder.build(), Item::class.java)
        assertEquals(2, searchHits.totalHits)
        searchHits.forEach(::println)
    }

    @Test
    @Order(13)
    fun testDelete(){
        itemRepository.deleteById(1)
        assertEquals(2, itemRepository.count())
    }

}