package code.lumiseven.demo.skt.springsecurity.controller

import code.lumiseven.demo.skt.jpa.entity.User
import org.springframework.security.access.prepost.PostAuthorize
import org.springframework.security.access.prepost.PostFilter
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.access.prepost.PreFilter
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class IndexController {

    /**
     * @PreAuthorize: 方法调用之前 基于表达式的计算结果 限制对方法的访问
     * @PostAuthorize: 方法调用之后 如果表达式的结果为 false 则抛出异常
     * @PreFilter: 方法调用之前 过滤进入方法的输入值
     * @PostFilter: 方法调用之后 过滤方法的返回值
     */

    // 任意角色都可访问
    @GetMapping("/")
    fun index(): String {
        return "Hello, Kotlin for Springboot!!"
    }

    // 访问前校验 User角色可访问
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/hello/pre/user")
    fun rolePreUserHello(): String {
        println("pre filter admin user")
        return "PreAuthorize user role"
    }

    // 访问后校验 User角色可访问
    @PostAuthorize("hasRole('USER')")
    @GetMapping("/hello/post/user")
    fun rolePostUserHello(): String {
        println("post filter user role")
        return "PostAuthorize user role"
    }

    // 访问前校验 Admin角色可访问
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/hello/admin")
    fun roleAdminHello(): String {
        println("pre filter admin user")
        return "PreAuthorize admin role"
    }

    // 访问前校验 User 和 Admin 都角色可访问
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/hello/any")
    fun anyRoleUserHello(): String {
        return "PreAuthorize user or admin role"
    }

    // 访问前校验 User角色可访问 过滤参数 仅传递 age > 50 的
    @PreFilter(value="hasRole('USER') and filterObject.age > 50")
    @PostMapping(value = ["/user/prefilter"], consumes = ["application/json"])
    fun preFilterUser(@RequestBody user: List<User>): List<User> {
        println("pre filter user")
        return user
    }

    // 访问后校验 User角色可访问 过滤参数 仅传递 age > 50 的
    @PostFilter(value="hasRole('USER') and filterObject.age > 50")
    @PostMapping("/user/postfilter")
    fun postFilterUser(@RequestBody user: List<User>): List<User> {
        println("post filter user")
        return user
    }

    // 访问前校验 Admin角色可访问 过滤参数 仅传递 age > 50 的
    @PreFilter(value="hasRole('ADMIN') and filterObject.age > 50")
    @PostMapping("/user/admin/prefilter")
    fun preFilterAdmin(@RequestBody user: List<User>): List<User> {
        println("pre filter user")
        return user
    }

    // 访问前校验 Admin角色可访问 过滤参数 仅传递 userName == test02 的
    @PreFilter(value="hasRole('ADMIN') and filterObject.userName.equals('test02')")
    @PostMapping("/user/prefilter1")
    fun preFilterUser1(@RequestBody user: List<User>): List<User> {
        println("pre filter user")
        return user
    }

}