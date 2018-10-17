package com.test.designPattern.Interface;

/**
 * Created by Administrator on 8/20/2018.
 */
public interface Subject {
    /*增加观察者*/
    void add(Observer observer);
    void del(Observer observer);
    void notifyObservers();
    void operation();
}
