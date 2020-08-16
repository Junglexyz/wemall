package com.jungle.wemall.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author : jungle
 */
@SpringBootApplication(
        scanBasePackages = {"com.jungle.wemall"}
)
@MapperScan("com.jungle.wemall.db.dao")
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
 
}
