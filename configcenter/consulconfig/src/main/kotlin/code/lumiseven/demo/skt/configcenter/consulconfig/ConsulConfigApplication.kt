package code.lumiseven.demo.skt.configcenter.consulconfig

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ConsulConfigApplication

fun main(args: Array<String>) {
    runApplication<ConsulConfigApplication>(*args)
}