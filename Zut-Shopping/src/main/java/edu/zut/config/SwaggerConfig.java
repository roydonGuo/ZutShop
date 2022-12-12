package edu.zut.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("edu.zut.controller"))
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("roydon", "https://www.roydon.top", "3133010060@qq.com");
        return new ApiInfoBuilder()
                .title("zut-shop")
                .description("学子商城")
                .contact(contact)   // 联系方式
                .version("1.0.0")  // 版本
                .build();
    }
}