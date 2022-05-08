package code.lumiseven.demo.skt.redis

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.util.Assert

@SpringBootTest
class RedisApplicationTests {

	@Autowired
	private lateinit var redisData: RedisData

	@Test
	fun contextLoads() {
	}

	// value
	@Test
	fun saveKeyValueWithExpireTime() {
		val key = "test_save_k1"
		runBlocking {
			redisData.saveStringWithExpire(key, "test_save_v1", 2L)
			delay(2500L)
		}
		println("get key")
		val value = redisData.getString(key)
		Assertions.assertTrue(value == null)
	}

	@Test
	fun saveAndGetValue() {
		val key = "test_save_k2"
		val value = "test_save_v2"
		redisData.deleteByKey(key)
		redisData.saveString(key, value)
		val valueGet = redisData.getString(key)
		Assertions.assertTrue(value == valueGet)
	}


	// list
	@Test
	fun saveListWithExpireTime() {
		val key = "list_key_1"
		redisData.deleteByKey(key)
		runBlocking {
			val values = listOf("list_msg_1", "list_msg_2", "list_msg_3", "list_msg_4")
			redisData.saveListWithExpire(key, values, 2L)
			delay(2500L)
		}
		val values = redisData.getListValue(key)
		Assertions.assertTrue(values?.size == 0)
	}

	@Test
	fun saveAndGetList() {
		val key = "list_key_2"
		val values = listOf("list_msg_1", "list_msg_2", "list_msg_3", "list_msg_4")
		redisData.deleteByKey(key)
		redisData.saveList(key, values)
		val valueGet = redisData.getListValue(key)
		Assertions.assertTrue(valueGet?.size == values.size)
	}


	// set
	@Test
	fun saveSetWithExpireTime() {
		val key = "set_key_1"
		runBlocking {
			val values = arrayOf("hello", "hello", "world")
			redisData.saveSetWithExpire(key, values, 2L)
			delay(2500L)
		}
		val values = redisData.getSetValues(key)
		Assertions.assertTrue(values?.size == 0)
	}

	@Test
	fun `save and get redis two set diff`() {
		val key1 = "set_key_1"
		val key2 = "set_key_2"
		redisData.deleteByKey(key1)
		redisData.deleteByKey(key2)
		redisData.saveSet(key1, arrayOf("hello", "hello", "world", "wide"))
		redisData.saveSet(key2, arrayOf("hello", "hello", "world", "women"))
		val diffSet = redisData.getSetDiff(key1, key2)
		Assertions.assertTrue(diffSet?.size == 1)
	}


	// zset
	@Test
	fun `save redis zset with expire time`() {
		val key = "zset_key_1"
		runBlocking {
			val values = arrayOf(Pair("xiaoming",98.0), Pair("xiaoli", 90.0), Pair("wangming", 100.0))
			redisData.saveZsetWithExpire(key, values, 2L)
			delay(2_000)
		}
		val values = redisData.getZsetRangeByScore(key, 95.0, 99.0)
		Assertions.assertTrue(values?.size == 0)
	}

	@Test
	fun `saven and get redis zset values by score`() {
		val key = "zset_key_1"
		redisData.deleteByKey(key)
		redisData.saveZset(key, arrayOf(Pair("xiaoming",98.0), Pair("xiaoli", 90.0), Pair("wangming", 100.0)))
		val values = redisData.getZsetRangeByScore(key, 95.0, 100.0)
		Assertions.assertTrue(values?.size == 2)
	}


	// hash
	@Test
	fun `save redis hashs with expire time`() {
		val key = "hash_key_1"
		runBlocking {
			val aMap = mapOf(Pair("key1","value1"), Pair("key2", "value2"))
			redisData.saveHashWithExpire(key, aMap, 2L)
			delay(2_000)
		}
		val values = redisData.getHashValues(key)
		Assertions.assertTrue(values?.size == 0)
	}

	@Test
	fun `save and get redis hash values`() {
		val key = "hash_key_1"
		redisData.deleteByKey(key)
		val aMap = mapOf(Pair("key1","value1"), Pair("key2", "value2"))
		redisData.saveHash(key, aMap)
		val values = redisData.getHashValues(key)
		Assertions.assertTrue(values?.size == 2)
	}

}
