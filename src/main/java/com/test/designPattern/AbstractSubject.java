package com.test.designPattern;

import com.test.designPattern.Interface.Observer;
import com.test.designPattern.Interface.Subject;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by Administrator on 8/20/2018.
 */
public abstract class AbstractSubject implements Subject{
    private Vector<Observer> vector = new Vector<>();

    @Override
    public void add(Observer observer) {
        vector.add(observer);
    }

    @Override
    public void del(Observer observer) {
        vector.remove(observer);
    }

    @Override
    public void notifyObservers() {
        Enumeration<Observer> enumo = vector.elements();
        while (enumo.hasMoreElements()){
            enumo.nextElement().update();
        }
    }
}
