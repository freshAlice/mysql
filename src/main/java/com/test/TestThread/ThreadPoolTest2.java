package com.test.TestThread;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * Created by Administrator on 8/17/2018.
 */
public class ThreadPoolTest2 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Set<Callable<String>> callables =  new HashSet<>();
        callables.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Task 1";
            }
        });
        callables.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Task 2";
            }
        });
        callables.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Task 3";
            }
        });
        callables.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Task 4";
            }
        });

        String result = null;
        try {
            result =executorService.invokeAny(callables);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("result = "+ result);

        List<Future<String>> futures = null;
        try {
            int i=0;
            futures = executorService.invokeAll(callables);
            for (Future future:futures){
                System.out.println((++i)+" "+future.get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //executorService.shutdown();
        executorService.shutdownNow();
    }
}
