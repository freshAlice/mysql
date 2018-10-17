package com.test.designPattern;

/**
 * Created by Administrator on 8/17/2018.
 * 饿汉式
 */
public class Singleton {
    //直接创建对象
    public static Singleton instance = new Singleton();

    //私有化构造函数
    private Singleton(){

    }
    //返回实例对象
    public static Singleton getInstance(){
        return instance;
    }
}
