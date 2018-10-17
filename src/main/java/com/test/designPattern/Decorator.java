package com.test.designPattern;

import com.test.designPattern.Interface.Sourceable;

/**
 * Created by Administrator on 8/20/2018.
 */
public class Decorator implements Sourceable {
    private Sourceable sourceable;

    public Decorator(Sourceable sourceable) {
        this.sourceable = sourceable;
    }

    @Override
    public void method() {
        System.out.println("before decorator");
        sourceable.method();
        System.out.println("after decorator");
    }

    public static void main(String[] args) {
        Sourceable sourceable = new Source();
        Sourceable obj = new Decorator(sourceable);
        obj.method();
    }
}
