package com.jungle.wemall.all;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author : Jungle
 * @EnableScheduling springboot 定时任务开启
 * @EnableTransactionManagement @Transactional注解生效
 */
@SpringBootApplication(
        scanBasePackages = {"com.jungle.wemall"}
)
@MapperScan("com.jungle.wemall.db.dao")
@EnableTransactionManagement
@EnableScheduling
public class Application {
 
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
 
}
