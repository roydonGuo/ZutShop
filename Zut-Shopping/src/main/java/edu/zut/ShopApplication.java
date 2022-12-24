package edu.zut;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Author: roydon - 2022/12/12
 **/
@Slf4j
@EnableSwagger2
@MapperScan("edu.zut.mapper")
@SpringBootApplication
public class ShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
        log.info("学子商城启动  ლ(´ڡ`ლ)ﾞ");
    }

}