package code.lumiseven.demo.skt.jpa

import code.lumiseven.demo.skt.jpa.entity.EducationLevel
import code.lumiseven.demo.skt.jpa.entity.QUser
import code.lumiseven.demo.skt.jpa.entity.User
import code.lumiseven.demo.skt.jpa.repository.UserDomainRepository
import com.querydsl.jpa.impl.JPAQueryFactory
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@ExtendWith(SpringExtension::class)
class UserDomainRepositoryTest {

    @Autowired
    private lateinit var queryFactory: JPAQueryFactory

    @Autowired
    private lateinit var userDomainRepository: UserDomainRepository

    @Test
    @Order(1)
    fun testSave() {
        userDomainRepository.deleteAll()
        val users = arrayOf(
            User(0, "test01", "test01", "test01@qq.com", 45, 175.5, "Shanghai", EducationLevel.BOSHI, 50000.0),
            User(1, "test02", "test02", "test02@qq.com", 36, 170.5, "Shanghai", EducationLevel.YANJIUSHENG, 20000.0),
            User(2, "test03", "test03", "test03@qq.com", 26, 165.5, "Shanghai", EducationLevel.YANJIUSHENG, 10000.0)
        )
        userDomainRepository.saveAll(users.asList())
    }

    @Test
    @Order(2)
    fun testFindByUserNameAndPassword() {
        val qUser = QUser.user
        val predicate = qUser.userName.eq("test01").and(qUser.password.eq("test01"))
        val user = queryFactory.selectFrom(qUser).where(predicate).fetchOne()
        assertEquals("test01@qq.com", user?.email)
    }

    @Test
    @Order(3)
    fun testFindByUserNameLike() {
        val qUser = QUser.user
        val predicate = qUser.userName.like("%test%")
        val users = queryFactory.selectFrom(qUser).where(predicate).fetch()
        assertEquals(3, users?.size)
    }

    @Test
    @Order(4)
    fun testFindByIncomeGreaterThan() {
        val qUser = QUser.user
        val predicate = qUser.income.gt(10000.0)
        val users = queryFactory.selectFrom(qUser).where(predicate).fetch()
        assertEquals(2, users.size)
    }

    @Test
    @Order(5)
    fun testFindByUserNameContains() {
        val qUser = QUser.user
        val predicate = qUser.userName.contains("test")
        val users = queryFactory.selectFrom(qUser).where(predicate).fetch()
        assertEquals(3, users.size)
    }

    @Test
    @Order(6)
    @Transactional
    @Rollback(false)
    fun testDeleteByUserNameAndEmail() {
        val qUser = QUser.user
        val predicate = qUser.userName.eq("test01").and(qUser.email.eq("test01@qq.com"))
        queryFactory.delete(qUser).where(predicate).execute()
        val users = queryFactory.selectFrom(qUser).fetch()
        assertEquals(2, users.size)
    }

    @Test
    @Order(7)
    @Transactional
    @Rollback(false)
    fun testUpdateEmailByUserName() {
        val qUser = QUser.user
        val predicate = qUser.userName.eq("test02")
        queryFactory.update(qUser).set(qUser.email, "test02@yy.com").where(predicate).execute()
        val user = queryFactory.selectFrom(qUser).where(predicate).fetchOne()
        assertEquals("test02@yy.com", user?.email)
    }

}