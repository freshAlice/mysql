package com.test.TestThread;

/**
 * Created by Administrator on 8/16/2018.
 */
public class RunnableCusToDec implements Runnable{
    private ShareData shareData;
    public RunnableCusToDec(ShareData shareData) {
        this.shareData = shareData;
    }

    @Override
    public void run() {
        for(int i=0;i<5;i++){
            shareData.dec();
        }
    }
}
