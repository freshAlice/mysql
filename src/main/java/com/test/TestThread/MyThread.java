package com.test.TestThread;

/**
 * Created by Administrator on 8/17/2018.
 */
public class MyThread extends Thread{
    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName()+" 正在执行...");
    }
}
