package code.lumiseven.demo.skt.seata.consumer.config

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import java.io.IOException
import javax.sql.DataSource

@Configuration
@EnableConfigurationProperties(MybatisProperties::class)
class DataSourceConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    fun dataSource(): DataSource {
        return DruidDataSource()
    }

    @Bean
    fun sqlSessionFactoryBean(dataSource: DataSource,
                              mybatisProperties: MybatisProperties): SqlSessionFactoryBean {
        val bean = SqlSessionFactoryBean()
        bean.setDataSource(dataSource)

        val resolver = PathMatchingResourcePatternResolver()
        try {
            val locations = resolver.getResources(mybatisProperties.mapperLocations[0])
            bean.setMapperLocations(*locations)

            if (!mybatisProperties.configLocation.isNullOrBlank()) {
                val resources = resolver.getResources(mybatisProperties.configLocation)
                bean.setConfigLocation(resources[0])
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return bean
    }
}