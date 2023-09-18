package com.luwen.concurrentTest;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author luwen
 */
@Slf4j
public class _6_lock {

    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<10;i++){
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("test");
                }
            });
            executorService.shutdown();
        }
    }

    public static void reentrantLock() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try{
                    Thread.sleep(1000 * 10);
                    log.info("child...");
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }).start();
        Thread.sleep(1000);
        lock.lock();
        try{
            log.info("main....");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public static void LockSupportTest() throws InterruptedException {

        log.info("main start ...");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("start ...");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LockSupport.park();
                log.info("end ...");
            }
        }, "thead1");
        thread.start();
        LockSupport.unpark(thread);
        log.info("main end ...");
    }
}
