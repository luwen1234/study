package com.luwen.concurrentTest._1_thread;

public class Test {

    public static Integer value = new Integer(1);

    public static void main(String[] args) throws InterruptedException {

/*        Test test = new Test();
        test.wait0Test();

        joinTest();

        daemonTest();*/
        interrupt();
    }

    public void wait0Test() throws InterruptedException {
        System.out.println("start wait0Test...., except wait 3 second...");
        synchronized (this){
            wait(3000);
            System.out.println("wait0Test...");
        }
    }

    /**
     * join为synchronized方法，
     * 调用wait(0)。即挂起，之后又竞争cpu使用时间
     * @throws InterruptedException
     */
    public static void joinTest() throws InterruptedException {

        System.out.println("joinTest start...");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName()+", method : join");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        thread.join();
        System.out.println("joinTest end...");
    }

    /**
     * Java中有两种线程，一种是用户线程，另一种是守护线程。
     * 用户线程是指用户自定义创建的线程，主线程停止，用户线程不会停止
     * 守护线程当进程不存在或主线程停止，守护线程也会被停止。
     * 使用setDaemon(true)方法设置为守护线程
     */
    public static void daemonTest(){

    }

    public static void interrupt() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true){
                        Thread.sleep(0);
                    }
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " end ");
                }
            }
        });
        thread.start();
        Thread.sleep(3000);
        thread.interrupt();
    }
}
