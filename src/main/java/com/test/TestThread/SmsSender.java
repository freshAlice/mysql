package com.test.TestThread;

import com.test.TestThread.Interface.Sender;

/**
 * Created by Administrator on 8/17/2018.
 */
public class SmsSender implements Sender {
    @Override
    public void Send() {
        System.out.println("this is sms sender!");
    }
}
