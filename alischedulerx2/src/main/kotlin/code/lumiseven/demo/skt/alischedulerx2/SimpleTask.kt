package code.lumiseven.demo.skt.alischedulerx2

import com.alibaba.schedulerx.worker.domain.JobContext
import com.alibaba.schedulerx.worker.processor.JavaProcessor
import com.alibaba.schedulerx.worker.processor.ProcessResult
import org.springframework.stereotype.Component

@Component
class MyHelloJob : JavaProcessor() {
    @Throws(Exception::class)
    override fun process(context: JobContext): ProcessResult {
        println("hello schedulerx2.0")
        return ProcessResult(true)
    }
}