package com.luwen.concurrentTest._5_threadExcutors;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * @author luwen
 */
public class Test {

    public static void main(String[] args) {
        Executor executor;
        AbstractExecutorService abstractExecutorService;
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3,3, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(3));
        poolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });
    }
}

class SerialExecutor implements Executor {
    final Queue<Runnable> tasks = new ArrayDeque<Runnable>();
    final Executor executor;
    Runnable active;

    SerialExecutor(Executor executor) {
        this.executor = executor;
    }

    @Override
    public synchronized void execute(final Runnable r) {
        tasks.offer(new Runnable() {
            @Override
            public void run() {
                try {
                    r.run();
                } finally {
                    scheduleNext();
                }
            }
        });
        if (active == null) {
            scheduleNext();
        }
    }
    protected synchronized void scheduleNext() {
        if ((active = tasks.poll()) != null) {
            executor.execute(active);
        }
    }
}

