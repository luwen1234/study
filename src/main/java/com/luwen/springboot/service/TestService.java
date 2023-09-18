package com.luwen.springboot.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Async
    public void loadTest() throws InterruptedException {
        System.out.println("load start...");
        Thread.sleep(10000);
        System.out.println("load end...");
    }

    /**
     * retryFor：抛出指定异常才会重试
     * maxAttempts：最大重试次数，默认3次
     * backoff：重试等待策略，
     * 默认使用@Backoff，@Backoff的value默认为1000L，我们设置为2000L；
     * multiplier（指定延迟倍数）默认为0，表示固定暂停1秒后进行重试，
     * 如果把multiplier设置为1.5，则第一次重试为2秒，第二次为3秒，第三次为4.5秒。
     * @param code
     * @return
     * @throws Exception
     */
/*    @Async
    @Retryable(value = Exception.class, maxAttempts = 2, backoff = @Backoff(delay = 1000, multiplier = 0))
    public Integer retryableTest(int code) throws Exception {
        System.out.println("test被调用,时间：" + LocalTime.now());
        if (code == 0) {
            throw new Exception("情况不对头！");
        }
        System.out.println("test被调用,情况对头了！");
        return 200;
    }*/
}
