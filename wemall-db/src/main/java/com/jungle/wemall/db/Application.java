package com.jungle.wemall.db;

//import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {"com.jungle.wemall"}
)
//@ComponentScan(basePackages = "com.jungle.wemall.dao")
@MapperScan("com.jungle.wemall.db.dao")
public class Application {
 
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
 
}
