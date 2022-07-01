package code.lumiseven.demo.skt.sentinel

import com.alibaba.csp.sentinel.annotation.SentinelResource
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import com.alibaba.csp.sentinel.slots.block.BlockException

@RestController
class SentinelController {
    /**
     * fallback是针对方法出现异常了，则会进入fallback方法
     * blockhandler是针对流控设置，超出规则，则会进入blockhandler方法
     */
    @GetMapping("/hello")
    @SentinelResource(value = "helloApi", blockHandler = "handleException", blockHandlerClass = [ExceptionUtil::class])
    fun hello(): String {
        return "hello sentinel"
    }

    @GetMapping("/hello1")
    @SentinelResource(value = "helloApi1", blockHandler = "exceptionHandler")
    fun hello1(): String {
        return "hello1 sentinel"
    }

    @GetMapping("/hello2")
    @SentinelResource(value = "helloConsulApi", blockHandler = "exceptionHandler")
    fun helloConsul(): String {
        return "hello2 sentinel in consul"
    }

    @GetMapping("/hello3")
    @SentinelResource(value = "helloConsulApi", blockHandler = "exceptionHandler")
    fun helloConsul3(): String {
        return "hello3 sentinel in consul"
    }

    fun exceptionHandler(ex: BlockException): String {
        println("exceptionHandler")
        return "Oops, error occurred"
    }
}