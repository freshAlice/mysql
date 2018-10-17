package com.test.TestThread;

import javafx.concurrent.ScheduledService;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import java.util.concurrent.*;

/**
 * Created by Administrator on 8/17/2018.
 */
public class ScheduleThreadTest {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        ScheduledFuture scheduledFuture = scheduledExecutorService.schedule(new Callable(){
            @Override
            public Object call() throws Exception {
                return "Called";
            }
        },5, TimeUnit.SECONDS);

        try {
            Thread.sleep(5000);
            System.out.println("scheduledFuture.get(): "+scheduledFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("--------------------");
        ScheduledExecutorService scheduledExecutorService1 = Executors.newScheduledThreadPool(5);
        ScheduledFuture scheduledFuture1 = scheduledExecutorService1.schedule(new Callable(){
            public Object call() throws Exception{
                System.out.println("Executed!");
                return "Called2";
            }
        },5,TimeUnit.SECONDS);

        try {
            System.out.println("scheduledFuture1.get():"+scheduledFuture1.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


}
