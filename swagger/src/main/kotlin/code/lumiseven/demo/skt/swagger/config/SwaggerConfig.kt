package code.lumiseven.demo.skt.swagger.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@Configuration
@EnableSwagger2
class SwaggerConfig: WebMvcConfigurer {
    @Bean
    fun createRestApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("code.lumiseven.demo.skt.swagger"))
            .paths(PathSelectors.any())
            .build()
    }

    @Bean
    fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
            .title("使用Swagger2构建Restful APIs")
            .contact(Contact("ls", "https://github.com/lumiseven", "lumiseven@yahoo.com"))
            .version("1.0")
            .description("Demo for book Kotlin Springboot Action")
            .build()
    }

    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addRedirectViewController("/api/v2/api-docs", "/v2/api-docs")
        registry.addRedirectViewController(
            "/api/swagger-resources/configuration/ui",
            "/swagger-resources/configuration/ui"
        )
        registry.addRedirectViewController(
            "/api/swagger-resources/configuration/security",
            "/swagger-resources/configuration/security"
        )
        registry.addRedirectViewController("/api/swagger-resources", "/swagger-resources")
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/api/swagger-ui.html**")
            .addResourceLocations("classpath:/META-INF/resources/swagger-ui.html")
        registry.addResourceHandler("/api/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/")
    }

}