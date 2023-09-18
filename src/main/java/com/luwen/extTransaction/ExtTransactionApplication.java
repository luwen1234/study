package com.luwen.extTransaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
/*@ComponentScan(basePackages = "com.extTransaction")
@MapperScan*/
public class ExtTransactionApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExtTransactionApplication.class, args);
    }
}
