package com.luwen.jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.Map;

public class _0_reference {

    public static void main(String[] args) {

    }

    /**
     * 虚引用：配置虚引用队列使用，被置空的虚引用对象进入虚引用队列，垃圾回收时由垃圾回收线程回收
     * @throws InterruptedException
     */
    public static void test() throws InterruptedException {
        ReferenceQueue queue = new ReferenceQueue<>();
        Map<String, String> map = new HashMap<>();
        map.put("a", "test");
        PhantomReference<Map<String, String>> phantomReference = new PhantomReference<>(map, queue);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(;;){
                    if(queue.poll() != null){
                        System.out.println("map...");
                    }
                }
            }
        });
        thread.start();
        Thread.sleep(3000);
        map = null;
        System.gc();
    }
}
