package com.luwen.concurrentTest._3_producerAndComsumer;

import org.springframework.util.StringUtils;

import java.util.concurrent.FutureTask;

public class Test {

    public static void main(String[] args) {
        Data data = new Data();
        new Thread(new Producer(data)).start();
        new Thread(new Consumer(data)).start();
    }
}

class Data{
    public String result;
}

class Producer implements Runnable{

    private Data data;

    public Producer(Data data){
        this.data = data;
    }

    @Override
    public void run() {
        while(true){
            synchronized ("lock"){
                if(StringUtils.isEmpty(this.data.result)){
                    String result = String.valueOf(Math.random());
                    System.out.println(Thread.currentThread().getName()+"生产数据: " + result);
                    this.data.result = result;
                    try {
                        Thread.sleep(1000L);
                        "lock".notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    try {
                        "lock".wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

class Consumer implements Runnable{

    private Data data;

    public Consumer(Data data){
        this.data = data;
    }

    @Override
    public void run() {
        while(true){
            synchronized ("lock"){
                if(StringUtils.isEmpty(this.data.result)){
                    try {
                        "lock".wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    System.out.println(Thread.currentThread().getName()+"消费数据："+this.data.result);
                    this.data.result = "";
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    "lock".notify();
                }
            }
        }
    }
}