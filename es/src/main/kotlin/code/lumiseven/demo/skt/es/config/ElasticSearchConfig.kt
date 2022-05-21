package code.lumiseven.demo.skt.es.config

import co.elastic.clients.elasticsearch.ElasticsearchClient
import co.elastic.clients.json.jackson.JacksonJsonpMapper
import co.elastic.clients.transport.ElasticsearchTransport
import co.elastic.clients.transport.rest_client.RestClientTransport
import code.lumiseven.demo.skt.es.util.TransportUtils
import org.apache.http.HttpHost
import org.apache.http.auth.AuthScope
import org.apache.http.auth.UsernamePasswordCredentials
import org.apache.http.impl.client.BasicCredentialsProvider
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder
import org.elasticsearch.client.RestClient
import org.elasticsearch.client.RestHighLevelClient
import org.elasticsearch.client.RestHighLevelClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate
import javax.net.ssl.SSLContext


@Configuration
class ElasticSearchConfig {

    /**
     * Verifying HTTPS with a certificate fingerprint(可从启动日志获取)
     * 配置方式可以参考: https://www.elastic.co/guide/en/elasticsearch/client/java-api-client/current/connecting.html
     */
    @Value("\${elasticsearch.host}")
    private val host: String? = null

    @Value("\${elasticsearch.port}")
    private val port: Int? = null

//    @Value("\${elasticsearch.fingerprint}")
//    private val fingerprint: String? = null
//
//    @Value("\${elasticsearch.username}")
//    private val username: String? = null
//
//    @Value("\${elasticsearch.password}")
//    private val password: String? = null

    @Bean
    fun getRestHighLevelClient(): RestHighLevelClient? {
//        val sslContext: SSLContext = TransportUtils.sslContextFromCaFingerprint(fingerprint)
//        val credsProv = BasicCredentialsProvider()
//        credsProv.setCredentials(
//            AuthScope.ANY, UsernamePasswordCredentials(username, password)
//        )
        val restClient = RestClient
            .builder(HttpHost(host, port!!, "http"/*"https"*/))
//            .setHttpClientConfigCallback { hc: HttpAsyncClientBuilder ->
//                hc
//                    .setSSLContext(sslContext)
//                    .setDefaultCredentialsProvider(credsProv)
//            }
            .build()

        /*
        es 8.0+
         */
//        val transport: ElasticsearchTransport = RestClientTransport(restClient, JacksonJsonpMapper())
//        return ElasticsearchClient(transport)


        /*
        使用 restHighLevelClient 7.17 兼容 es8
         */
        return RestHighLevelClientBuilder(restClient)
            .setApiCompatibilityMode(true)// 使用 restHighLevelClient 7.17 兼容 es8
            .build()
    }

    @Bean
    fun elasticsearchTemplate(client: RestHighLevelClient): ElasticsearchOperations? {
        return ElasticsearchRestTemplate(client)
    }

}