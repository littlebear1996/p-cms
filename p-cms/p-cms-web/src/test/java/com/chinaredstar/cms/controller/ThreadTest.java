package com.chinaredstar.cms.controller;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ykk on 2017/3/24.
 */
public class ThreadTest implements Runnable{

    private  int num;
    private CountDownLatch countDownLatch;

    ThreadTest(int num,CountDownLatch countDownLatch){
        this.num = num;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
                         TimeUnit.SECONDS.sleep(new Random().nextInt(10));
                     } catch (InterruptedException e) {
                         // TODO Auto-generated catch block
                         e.printStackTrace();
                     }
                 System.out.println("Student "+num+" finished!");
        countDownLatch.countDown();
    }

    public static void main(String[] args) {
        /*ExecutorService executor = Executors.newCachedThreadPool();

                 CountDownLatch latch = new CountDownLatch(3);

        ThreadTest s1 = new ThreadTest(101, latch);
        ThreadTest s2 = new ThreadTest(102, latch);
        ThreadTest s3 = new ThreadTest(103, latch);

                 executor.execute(s1);
                 executor.execute(s2);
                 executor.execute(s3);
        try {
            latch.await();
            System.out.println("全部执行完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
        executor.shutdown();*/

    }
}
