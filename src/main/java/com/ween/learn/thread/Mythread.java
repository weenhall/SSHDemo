package com.ween.learn.thread;

/**
 * Created by wen on 2017/6/7.
 * 实现多线程的两种方式：继承Thread类，实现Runnable接口
 * 1.继承Thread类
 *   重写run()方法，启动线程
 */
public class Mythread extends Thread{
    @Override
    public void run() {
        System.out.println("MyThread runnining");
    }

    public static void main(String[] args) {
        Mythread mythread=new Mythread();
        mythread.start();
    }
}
