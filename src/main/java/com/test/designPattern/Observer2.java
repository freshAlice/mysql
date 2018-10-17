package com.test.designPattern;

import com.test.designPattern.Interface.Observer;

/**
 * Created by Administrator on 8/20/2018.
 */
public class Observer2 implements Observer{
    @Override
    public void update() {
        System.out.println("Observer2 has received!");
    }
}
