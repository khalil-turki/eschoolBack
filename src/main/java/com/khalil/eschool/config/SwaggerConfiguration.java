package com.khalil.eschool.config;

import io.swagger.annotations.Api;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.util.Collections;

import static com.khalil.eschool.utils.Constants.APP_ROOT;


@Configuration

    @EnableSwagger2
    @EnableWebMvc
    @Component



    public class SwaggerConfiguration {


    //   public static final String AUTHORIZATION_HEADER = "Authorization";

    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(
                        new ApiInfoBuilder()
                                .description("eschoolBack API documentation")
                                .title("eschoolBack REST API")
                                .build()
                )
                .groupName("REST API V1")
                // .securityContexts(Collections.singletonList(securityContext()))
                //  .securitySchemes(Collections.singletonList(apiKey()))
                //   .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.khalil.eschool"))
                .paths(PathSelectors.ant(APP_ROOT + "/**"))
                .build();
    }

}
