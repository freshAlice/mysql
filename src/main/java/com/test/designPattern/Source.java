package com.test.designPattern;

import com.test.designPattern.Interface.Sourceable;

/**
 * Created by Administrator on 8/20/2018.
 */
public class Source implements Sourceable{
    @Override
    public void method() {
        System.out.println("the original method!");
    }
}
