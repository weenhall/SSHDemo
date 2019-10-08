package com.ween.learn.thread;

/**
 * Created by wen on 2017/6/7.
 * 实现多线程的两种方式：继承Thread类，实现Runnable接口
 * 2.实现Runnable接口
 *  重写run()方法
 */
public class MyRunnable implements Runnable{
    public void run() {
        System.out.println("MyRunnable running");
    }

    public static void main(String[] args) {
        Thread thread=new Thread(new MyRunnable());
        thread.start();
    }
}
