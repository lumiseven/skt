package code.lumiseven.demo.skt.springsecurity

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["code.lumiseven.demo.skt"])
class SpringsecurityApplication

fun main(args: Array<String>) {
	runApplication<SpringsecurityApplication>(*args)
}
