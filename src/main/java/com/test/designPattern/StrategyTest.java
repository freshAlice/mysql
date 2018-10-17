package com.test.designPattern;

import com.test.designPattern.Interface.ICalculator;

/**
 * Created by Administrator on 8/20/2018.
 */
public class StrategyTest {
    public static void main(String[] args) {
        String exp = "2+8";
        ICalculator cal = new Plus();
        int result = cal.calculate(exp);
        System.out.println(result);

        String exp2 = "8-2";
        ICalculator cal2 = new Minus();
        int result2 = cal2.calculate(exp2);
        System.out.println(result2);
    }
}
