package com.test.designPattern;

/**
 * Created by Administrator on 8/17/2018.
 * 懒汉式
 */
public class Singleton2 {
    //申明变量
    private static volatile Singleton2 singleton= null;
    //私有构造函数
    private Singleton2(){

    }
    //提供对外方法
    public static Singleton2 getInstance(){
        if(singleton==null){
            synchronized (Singleton2.class){
                singleton = new Singleton2();
            }
        }
        return singleton;
    }


}
