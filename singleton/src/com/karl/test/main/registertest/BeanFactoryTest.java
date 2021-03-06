package com.karl.test.main.registertest;

import com.karl.test.register.BeanFactory;

import java.util.concurrent.CountDownLatch;

public class BeanFactoryTest {

    public static void main(String[] args) {
        int count = 200;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            new Thread(){
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Object obj = BeanFactory.newInstance("com.karl.test.register.Person");
                    System.out.println(System.currentTimeMillis() + " : " + obj);
                }
            }.start();
            countDownLatch.countDown();
        }
        long end = System.currentTimeMillis();
        System.out.println("总时间： " + (end - start));
    }

}
