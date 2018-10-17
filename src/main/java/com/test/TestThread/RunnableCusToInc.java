package com.test.TestThread;

/**
 * Created by Administrator on 8/16/2018.
 */
public class RunnableCusToInc implements Runnable{
    private ShareData shareData;
    public RunnableCusToInc(ShareData shareData) {
        this.shareData = shareData;
    }


    @Override
    public void run() {
        for (int i=0;i<5;i++){
            shareData.inc();
        }
    }

    public static void main(String[] args) {
        /*ShareData shareData = new ShareData();
        for(int i = 0;i<4;i++){
            new Thread(new RunnableCusToInc(shareData), " Thread "+i).start();
        }*/
        ShareData shareData = new ShareData();
        for(int i=0;i<4;i++){
            if(i%2==0){
                new Thread(new RunnableCusToInc(shareData),"Thread"+i).start();
            }else {
                new Thread(new RunnableCusToDec(shareData),"Thread"+i).start();
            }
        }


    }
}
