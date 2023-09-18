package com.luwen.springboot.controller;

import com.test.springboot.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    private TestService testService;

    @GetMapping("/test")
    public String test() throws InterruptedException {
        System.out.println("test start...");
        testService.loadTest();
        System.out.println("test end...");
        return "resource Name : a";
    }

/*    @GetMapping("/retryable")
    public String retryable() throws Exception {
        System.out.println("relay start...");
        testService.retryableTest(0);
        System.out.println("relay end...");
        return "resource Name : retryable";
    }*/
}
