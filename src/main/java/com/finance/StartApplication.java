package com.finance;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author : shenhao
 * @date : 2020/3/24 16:28
 */

@SpringBootApplication
@EnableSwagger2
@MapperScan(basePackages = "com.finance.web.mapper")
public class StartApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }

}
