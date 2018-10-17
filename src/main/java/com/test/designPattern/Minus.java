package com.test.designPattern;

import com.test.designPattern.Interface.ICalculator;

/**
 * Created by Administrator on 8/20/2018.
 */
public class Minus extends AbstractCalculator implements ICalculator{
    @Override
    public int calculate(String exp) {
        int arrayInt[] = split(exp,"\\-");
        return arrayInt[0]-arrayInt[1];
    }
}
