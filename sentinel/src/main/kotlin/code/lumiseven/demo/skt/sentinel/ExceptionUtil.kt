package code.lumiseven.demo.skt.sentinel

import com.alibaba.csp.sentinel.slots.block.BlockException

class ExceptionUtil {
    fun handleException(ex: BlockException): String {
        ex.printStackTrace()
        return "Oops, error occurred in ExceptionUtil.handleException"
    }
}