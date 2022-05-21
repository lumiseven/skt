package code.lumiseven.demo.skt.es

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EsApplication

fun main(args: Array<String>) {
    runApplication<EsApplication>(*args)
}
