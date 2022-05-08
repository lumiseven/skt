package code.lumiseven.demo.skt.jpa.repository

import code.lumiseven.demo.skt.jpa.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor

interface UserDomainRepository: JpaRepository<User, Long>, QuerydslPredicateExecutor<User>
