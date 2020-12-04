package com.elk.app.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.AuthorizationScopeBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.ApiKey
import springfox.documentation.service.AuthorizationScope
import springfox.documentation.service.SecurityReference
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig : WebMvcConfigurationSupport() {

    @Bean
    fun appApi(): Docket {
        val authScopes = arrayOfNulls<AuthorizationScope>(1)
        authScopes[0] =
            AuthorizationScopeBuilder().scope("global").description("full access").build()
        val securityReference = SecurityReference.builder().reference("Bearer")
            .scopes(authScopes).build()
        val securityContexts = listOf(
            SecurityContext.builder().securityReferences(listOf(securityReference)).build()
        )

        return Docket(DocumentationType.SWAGGER_2)
            .securitySchemes(listOf(ApiKey("Bearer", "Authorization", "header")))
            .securityContexts(securityContexts).apiInfo(apiInfo()).select()
            .apis(
                RequestHandlerSelectors.basePackage(
                    "com.elk.app.controller"
                )
            )
            .paths(PathSelectors.any())
            .build()
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder().title("Product Management Rest API's")
            .description("Creates new Products & fetch with pagination when required")
            .version("0.0.1").build()
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/")
        registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/")
    }
}
