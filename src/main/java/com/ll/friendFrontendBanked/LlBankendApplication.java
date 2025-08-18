package com.ll.friendFrontendBanked;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ll.friendFrontendBanked.mapper")
public class LlBankendApplication {

    public static void main(String[] args) {
        SpringApplication.run(LlBankendApplication.class, args);
    }

}
