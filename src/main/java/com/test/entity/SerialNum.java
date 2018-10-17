package com.test.entity;

/**
 * Created by Administrator on 8/15/2018.
 */
public class SerialNum {
    private static int nextSerialNum=0;
    private static ThreadLocal serialNum = new ThreadLocal(){
        protected synchronized Object initialValue(){
            return new Integer(nextSerialNum++);
        }
    };
    public static int get(){
        return ((Integer)(serialNum.get())).intValue();
    }
}
