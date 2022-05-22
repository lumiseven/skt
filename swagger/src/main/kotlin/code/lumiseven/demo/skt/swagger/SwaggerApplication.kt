package code.lumiseven.demo.skt.swagger

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@SpringBootApplication
@ComponentScan(basePackages = ["code.lumiseven.demo.skt"])
@EnableWebMvc
class SwaggerApplication

fun main(args: Array<String>) {
    runApplication<SwaggerApplication>(*args)
}
