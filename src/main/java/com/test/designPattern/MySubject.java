package com.test.designPattern;

import com.test.designPattern.Interface.Subject;

/**
 * Created by Administrator on 8/20/2018.
 */
public class MySubject extends AbstractSubject{
    @Override
    public void operation() {
        System.out.println("update self!");
        notifyObservers();
    }

    public static void main(String[] args) {
        Subject subject = new MySubject();
        subject.add(new Observer1());
        subject.add(new Observer2());
        subject.operation();
    }
}
