package com.ll.llbankend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ll.llbankend.mapper")
public class LlBankendApplication {

    public static void main(String[] args) {
        SpringApplication.run(LlBankendApplication.class, args);
    }

}
