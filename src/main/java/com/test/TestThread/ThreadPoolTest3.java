package com.test.TestThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 8/17/2018.
 */
public class ThreadPoolTest3 {
    public static void main(String[] args) {
        //池中保存的线程数，包括空闲线程
        int corePoolSize = 5;
        //池中允许的最大线程数.
        int maxPoolSize = 10;
        //当线程大于核心数时，此为终止多余线程等待任务的最长时间
        long keepAliveTime =5000;
        ExecutorService threadPoolExecutor = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveTime,
                TimeUnit.MILLISECONDS,//keepAliveTime的时间单位
                new LinkedBlockingQueue<Runnable>()//执行当前用于保持任务的队列，此队列保持由execute方法提交的Runnable任务
        );
    }
}
