package com.luwen.applicationContentTest.entity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectConfig {

    @Bean
    public Person person(){
        Person person = new Person();
        person.setName("小王");
        person.setRemark("哈哈");
        return person;
    }
}
