package code.lumiseven.demo.skt.alioss

import com.aliyun.oss.OSS
import com.aliyun.oss.model.ObjectMetadata
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.io.IOException
import java.io.InputStream
import java.util.*

@RestController
class OssImageController {
    @Autowired
    lateinit var ossClient: OSS

    val imageExpireTime = 10 * 365 * 24 * 60 * 60 * 1000L;

    @GetMapping("/image/{name}")
    fun getImageUrl(@PathVariable name: String): String? {
        val expiration = Date(Date().time + imageExpireTime)
        val url = ossClient.generatePresignedUrl("lumi-alioss-bucket-private-3", "test/$name", expiration)
        return url?.toString()
    }

    @GetMapping("/image/upload/{name}")
    fun uploadImage(@PathVariable name: String): String {
        var ret = ""
        var stream: InputStream? = null
        try {
            val objectMetadata = ObjectMetadata()
            stream = this.javaClass.classLoader.getResourceAsStream("images/$name")
            objectMetadata.contentLength = stream.available().toLong()
            objectMetadata.cacheControl = "no-cache";
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.contentType = "image/jpg"
            objectMetadata.contentDisposition = "inline;filename=$name"
            val putObject = ossClient.putObject("lumi-alioss-bucket-private-3", "test/$name", stream, objectMetadata)
            ret = putObject.eTag
        } catch (e: IOException) {
            println("upload file to oss error name=$name")
        } finally {
            try{
                stream?.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return ret
    }
}