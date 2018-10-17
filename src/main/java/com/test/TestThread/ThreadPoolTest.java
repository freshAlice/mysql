package com.test.TestThread;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.*;

/**
 * Created by Administrator on 8/17/2018.
 */
public class ThreadPoolTest {
    /*//创建固定大小的线程池
    ExecutorService fpool = Executors.newFixedThreadPool(3);
    //创建缓存大小的线程池
    ExecutorService cPool = Executors.newCachedThreadPool();
    //创建单一的线程池
    ExecutorService aPool = Executors.newSingleThreadExecutor();*/

    public static void main(String[] args) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
        Thread t1 = new MyThread();
        Thread t2 = new MyThread();
        Thread t3 = new MyThread();
        Thread t4 = new MyThread();
        Thread t5 = new MyThread();
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.schedule(t4,2000, TimeUnit.MILLISECONDS);
        pool.schedule(t5,2000, TimeUnit.MILLISECONDS);
        pool.shutdown();

        System.out.println("--Asynchronous task!--");
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("--Asynchronous task!--"+Thread.currentThread().getName()+" 正在执行...");
            }
        });

        System.out.println("--submit--");
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        Future future = executorService.submit(new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println("Asynchronous Callable.");
                return  "Callable Result";
            }
        });
        try {
            System.out.println("future.get() = "+future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();

    }


}
