package com.test.entity;

/**
 * Created by Administrator on 8/15/2018.
 */
public class Business{
    private boolean subFlag = true;
    public synchronized void mainMethod(){
        while (subFlag){
            try {
                wait();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        for(int i=0;i<5;i++){
            System.out.println("--SerialNum--:" + SerialNum.get());
            System.out.println(Thread.currentThread().getName() +" main thread running loop count-- " + i);
            try {
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        subFlag = true;
        notify();
    }

    public synchronized void subMethod(){
        while (!subFlag){
            try {
                wait();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        for(int i=0;i<10;i++){
            System.out.println("--SerialNum--:" + SerialNum.get());
            System.out.println(Thread.currentThread().getName() + ": sub Thread running loop count -- "+i);
            try {
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        subFlag = false;
        notify();
    }
}