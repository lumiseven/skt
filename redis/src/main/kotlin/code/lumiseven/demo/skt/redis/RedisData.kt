package code.lumiseven.demo.skt.redis

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.time.Duration
import java.util.concurrent.TimeUnit

@Component
class RedisData {

    @Autowired
    private lateinit var stringRedisTemplate: RedisTemplate<String, String>

    // 通过 key 删除
    fun deleteByKey(key: String): Boolean {
        return stringRedisTemplate.delete(key)
    }

    /**
     * value
     */
    // 新增 k, v
    fun saveString(key: String, value: String) {
        stringRedisTemplate.opsForValue().set(key, value)
    }

    // 新增 k, v, timeout
    fun saveStringWithExpire(key: String, value: String, expiredSecond: Long) {
        stringRedisTemplate.opsForValue().set(key, value, Duration.ofSeconds(expiredSecond))
    }

    // 通过 key 查询
    fun getString(key: String): String? {
        return stringRedisTemplate.opsForValue().get(key)
    }


    /**
     * list
     */
    // 新增
    fun saveList(key: String, values: List<String>) {
        values.forEach {
            stringRedisTemplate.opsForList().leftPush(key, it)
        }
    }

    // 新增 + timeout
    fun saveListWithExpire(key: String, values: List<String>, expiredSecond: Long) {
        saveList(key, values)
        stringRedisTemplate.expire(key, expiredSecond, TimeUnit.SECONDS)
    }

    // 范围查询 list
    fun getListValue(key: String, start: Long = 0, end: Long = -1): List<String>? {
        return stringRedisTemplate.opsForList().range(key, start, end)
    }


    /**
     * set
     */
    // 新增
    fun saveSet(key: String, values: Array<String>) {
        stringRedisTemplate.opsForSet().add(key, *values)
    }

    // 新增 + timeout
    fun saveSetWithExpire(key: String, values: Array<String>, expiredSecond: Long) {
        saveSet(key, values)
        stringRedisTemplate.expire(key, expiredSecond, TimeUnit.SECONDS)
    }

    // 通过 key 查询
    fun getSetValues(key: String): Set<String>?{
        return stringRedisTemplate.opsForSet().members(key)
    }

    // 获取连个 set 中不相同的元素
    fun getSetDiff(k1: String, k2: String): Set<String>? {
        return stringRedisTemplate.opsForSet().difference(k1, k2)
    }

    /**
     * zset
     */
    // 新增
    fun saveZset(key: String, values: Array<Pair<String, Double>>) {
        values.forEach {
            stringRedisTemplate.opsForZSet().add(key, it.first, it.second)
        }
    }

    // 新增 + timeout
    fun saveZsetWithExpire(key: String, values: Array<Pair<String, Double>>, expiredSecond: Long) {
        saveZset(key, values)
        stringRedisTemplate.expire(key, expiredSecond, TimeUnit.SECONDS)
    }

    // score 范围查询 zset
    fun getZsetRangeByScore(key: String, minScore: Double, maxScore: Double): Set<String>? {
        return stringRedisTemplate.opsForZSet().rangeByScore(key, minScore, maxScore)
    }

    /**
     * hash
     */
    // 新增
    fun saveHash(key: String, values: Map<String, String>) {
        stringRedisTemplate.opsForHash<String, String>().putAll(key, values)
    }

    // 新增 + timeout
    fun saveHashWithExpire(key: String, values: Map<String, String>, expiredSecond: Long) {
        saveHash(key, values)
        stringRedisTemplate.expire(key, expiredSecond, TimeUnit.SECONDS)
    }

    // 获取 map 中的所有 value
    fun getHashValues(key: String): List<String>? {
        return stringRedisTemplate.opsForHash<String, String>().values(key)
    }

}