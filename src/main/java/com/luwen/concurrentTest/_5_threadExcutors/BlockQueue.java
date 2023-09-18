package com.luwen.concurrentTest._5_threadExcutors;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author luwen
 */
public class BlockQueue {

    private static BlockingQueue blockingQueue = new LinkedBlockingQueue<String>(1);

    public static void main(String[] args) {
        blockQueueTest();
    }

    public static void blockQueueTest() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    blockingQueue.put("1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(blockingQueue.offer("1"));
                System.out.println(Thread.currentThread().getName() + " executed... ");
            }
        }, "thread1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(blockingQueue.offer("2"));
                System.out.println(Thread.currentThread().getName() + " executed... ");
            }
        }, "thread2").start();
    }
}
