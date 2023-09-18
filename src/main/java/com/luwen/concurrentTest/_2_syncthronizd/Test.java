package com.luwen.concurrentTest._2_syncthronizd;

public class Test {

    public static Object lock1 = new Object();

    public static void main(String[] args) throws InterruptedException {
        memoryTest();

        /*        deadLockTest();*/

/*        synchronizedTest();*/

        SortedThreadTest();
    }

    /**
     * 共享内存模型指的就是Java内存模型(简称JMM)，JMM决定一个线程对共享变量的写入时,能对另一个线程可见。
     * 从抽象的角度来看，JMM定义了线程和主内存之间的抽象关系：
     * 线程之间的共享变量存储在主内存（main memory）中，每个线程都有一个私有的本地内存（local memory），
     * 本地内存中存储了该线程以读/写共享变量的副本。本地内存是JMM的一个抽象概念，并不真实存在。
     * 它涵盖了缓存，写缓冲区，寄存器以及其他的硬件和编译器优化。
     */
    public static void memoryTest() {

    }

    /**
     * runnable有多把锁可能发生死锁
     */
    public static void deadLockTest() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    deadLockMethod1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    deadLockMethod2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
        thread2.start();
        System.out.println("main : " + Thread.currentThread().getName());
    }

    private static synchronized void deadLockMethod1() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName() + " get deadLockMethod1");
        deadLockMethod2();
    }

    private static void deadLockMethod2() throws InterruptedException {
        synchronized (lock1) {
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " get deadLockMethod2");
            deadLockMethod1();
        }
    }

    /**
     * volatile保证内存模型中不同线程之间共享数据的可见性
     */
    public static void synchronizedTest() throws InterruptedException {

        SynchronizedTestThread thread = new SynchronizedTestThread(new Data());
        thread.start();
        Thread.sleep(1000);
        thread.setData1(1);
    }

    /**
     * 多次计算存在如下结果
     * false
     * 44733
     * 说明test1进行了指令重排
     */
    public static void SortedThreadTest() throws InterruptedException {
        SortedThread sortedThread = new SortedThread();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sortedThread.test1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Thread.sleep(50L);
        new Thread(new Runnable() {
            @Override
            public void run() {
                sortedThread.test2();
            }
        }).start();
    }

    public static void ThreadLocalTest(){
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("test");
    }
}

class Data{
    public int count=0;
}
class SynchronizedTestThread extends Thread {

    /**
     * 保存主线程和子线程数据可见性
     */
    public volatile int data = 0;

    public Data data1;

/*    public int data = 0;*/

    public SynchronizedTestThread(){

    }

    public SynchronizedTestThread(Data data1){
        this.data1 = data1;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setData1(int data1) {
        this.data1.count = data1;
    }

    @Override
    public void run() {
        while(this.data1.count == 0){
            // printlnw为synchronized，
            // synchronized会同步子线程工作内存和主线程内存状态
/*            System.out.println(this.data);*/
        }
    }
}

class SortedThread {

    public int a = 0;
    public boolean flag = false;

    public void test1() throws InterruptedException {

        for(int i=0;i<20000L;i++){
            this.a += 1;
        }
        for(int i=0;i<20000L;i++){
            this.a += 1;
        }
        this.flag =true;
        for(int i=0;i<20000L;i++){
            this.a += 1;
        }
    }

    public void test2(){
        System.out.println(this.flag);
        System.out.println(this.a);
    }
}