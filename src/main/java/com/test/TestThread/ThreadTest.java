package com.test.TestThread;

import com.test.entity.Business;
import com.test.entity.SerialNum;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Administrator on 8/15/2018.
 */
public class ThreadTest {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();//a队列
        Queue<String> queue2 = new LinkedList<>();//b队列
        ArrayList<String> a = new ArrayList<>();
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");
        queue.offer("d");
        queue.offer("e");
        System.out.println("进栈:");
        //a队列依次加入list集合之中
        for(String q:queue){
            a.add(q);
            System.out.println(q);
        }
        for(int i=a.size()-1;i>=0;i--){
            queue2.offer(a.get(i));
        }
        //打印出栈队列
        System.out.println("出栈:");
        for(String q:queue2){
            System.out.println(q);
        }
        System.out.println("--------------测试线程--------------");
        final Business business = new Business();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<3;i++){
                    business.subMethod();
                }
            }
        }).start();

        //主线程
        for(int i=0;i<3;i++){
            business.mainMethod();
        }

    }
}
