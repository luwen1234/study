package com.luwen.concurrentTest._4_futureTask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author luwen
 */
public class FutureTaskTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        futureTaskTest();
    }

    public static void futureTaskTest() throws ExecutionException, InterruptedException {

        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName() + " is running");
                return "it is futureTask schema test....";
            }
        });
        Thread thread = new Thread(futureTask);
        thread.start();
        System.out.println(Thread.currentThread().getName()+" is running ....");
        System.out.println("wait for result : " + futureTask.get());
        System.out.println(Thread.currentThread().getName() + " end ...");
    }
}
