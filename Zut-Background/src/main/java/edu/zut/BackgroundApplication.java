package edu.zut;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Author: roydon - 2022/12/21
 **/
@Slf4j
@SpringBootApplication
@MapperScan("edu.zut.mapper")
public class BackgroundApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackgroundApplication.class, args);
        log.info("后台启动中...");
    }
}
