package org.ricone.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;

@Configuration
@EnableSwagger2
@PropertySource("classpath:database.properties")

//http://www.baeldung.com/swagger-2-documentation-for-spring-rest-api

public class SwaggerConfig
{
    @Resource
    private Environment env;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.ricone.api.controller"))
                .paths(PathSelectors.ant("/requests/*"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo()
    {
        ApiInfo apiInfo = new ApiInfo(
                "xPress",
                "This is a sandbox that provides a preview of the RIC One xPress Roster API. <br/> <ol><li>Click the 'Authorize' button and select a token from the dropdown list and press 'Login'. Once signed in, click 'Explore'.</li>  <li>Explore the options with the various parameters you can set.</li></ol>",
                env.getRequiredProperty("info.app.version"), //Version
                null,
                "",
                null,
                null);
        return apiInfo;
    }
}