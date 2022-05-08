package code.lumiseven.demo.skt.jpa

import code.lumiseven.demo.skt.jpa.entity.EducationLevel
import code.lumiseven.demo.skt.jpa.entity.User
import code.lumiseven.demo.skt.jpa.repository.UserRepository
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@ExtendWith(SpringExtension::class)
class UserRepositoryTest {

    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    @Order(1)
    fun testSaveUsers() {
        userRepository.deleteAll()
        val users = arrayOf(
            User(0, "test01", "test01", "test01@qq.com", 45, 175.5, "Shanghai", EducationLevel.BOSHI, 50000.0),
            User(1, "test02", "test02", "test02@qq.com", 36, 170.5, "Shanghai", EducationLevel.YANJIUSHENG, 20000.0),
            User(2, "test03", "test03", "test03@qq.com", 26, 165.5, "Shanghai", EducationLevel.YANJIUSHENG, 10000.0)
        )
        userRepository.saveAll(users.asList())
        val users2 = userRepository.findAll()
        assertEquals(users2.toList().size, 3)
    }

    @Test
    @Order(2)
    fun testFindByUserNameAndPassword() {
        val user = userRepository.findByUserNameAndPassword("test01", "test01")
        assertEquals("test01@qq.com", user?.email)
        assertEquals(45, user?.age)
        assertEquals(175.5, user?.height)
        assertEquals(EducationLevel.BOSHI, user?.education)
    }

    @Test
    @Order(3)
    fun testFindByUserNameLike() {
        val users = userRepository.findByUserNameLike("%test%")
        assertEquals(3, users?.size)
    }

    @Test
    @Order(4)
    fun testFindByIncomeGreaterThan() {
        val users = userRepository.findByIncomeGreaterThan(10000.0)
        assertEquals(2, users?.size)
    }

    @Test
    @Order(5)
    fun testFindByUserNameContains() {
        val users = userRepository.findByUserNameContains("test")
        assertEquals(3, users?.size)
    }

    @Test
    @Order(6)
    fun testDeleteByUserNameAndEmail() {
        userRepository.deleteByUserNameAndEmail("test01", "test01@qq.com")
        val users = userRepository.findAll().toList()
        assertEquals(2, users.size)
    }

    @Test
    @Order(7)
    fun testSave() {
        val user = User(3, "test04", "test04", "test04@qq.com", 26, 165.5, "Shanghai", EducationLevel.YANJIUSHENG, 10000.0)
        userRepository.save(user)
        val users = userRepository.findAll().toList()
        assertEquals(3, users.size)
    }
    
}