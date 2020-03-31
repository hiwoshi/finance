package com.finance.config;

import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : shenhao
 * @date : 2019/10/8 13:18
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${background.swagger.defaultToken:21748DDA7AE4F5A70A213A916ADFBA34}")
    private String defaultToken;

    @Bean
    public Docket createRestApi() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("authorization").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).defaultValue(defaultToken).build();
        pars.add(tokenPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.and(Predicates.not(PathSelectors.ant("/error")),PathSelectors.any()))
                .build()
                .globalOperationParameters(pars)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("swagger构建api文档")
                .description("简单优雅的restfun风格")
                .version("1.0")
                .build();
    }

}
