package com.test.designPattern;

import com.test.TestThread.Interface.Sender;
import com.test.TestThread.MailSender;
import com.test.TestThread.SmsSender;

/**
 * Created by Administrator on 8/17/2018.
 * 工厂方法模式：普通工厂模式
 */
public class FactoryMethod {
    public Sender produce(String type){
        if("mail".equals(type)){
            return new MailSender();
        }else if("sms".equals(type)){
            return new SmsSender();
        }else {
         return  null;
        }
    }

    public Sender produceMail(){
        return new MailSender();
    }
    public SmsSender produceSms(){
        return new SmsSender();
    }
}
