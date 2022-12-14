package code.lumiseven.demo.skt.jpa.repository

import code.lumiseven.demo.skt.jpa.entity.User
import org.springframework.data.repository.CrudRepository
import org.springframework.transaction.annotation.Transactional

interface UserRepository: CrudRepository<User, Long> {

    fun findByUserNameAndPassword(userName: String, password: String): User?

    fun findByUserName(userName: String): User?

    fun findByUserNameLike(userName: String): List<User>?

    fun findByIncomeGreaterThan(income: Double): List<User>?

    fun findByUserNameContains(userName: String): List<User>?

    @Transactional(rollbackFor = [Exception::class])
    fun deleteByUserNameAndEmail(userName: String, email: String): Int?

    fun save(use: User)

}