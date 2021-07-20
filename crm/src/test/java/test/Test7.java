package test;

import java.util.Arrays;

/**
 * @author conrad
 * @date 2021/07/15
 */
public class Test7 {
    public static void main(String[] args) {
        Arrays.sort(new int[0]);
        Thread myThread = new MyThread();
        myThread.start();

        try {
            Thread.sleep(1000);
            myThread.interrupt();
            System.out.println(Thread.interrupted());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static class MyThread extends Thread{
        @Override
        public void run() {
            super.run();

            try {
                System.out.println("start.........");
                Thread.sleep(20000);
                System.out.println("mythread:----"+currentThread());
            } catch (InterruptedException e) {
                System.out.println("mythread:----"+Thread.interrupted());
                e.printStackTrace();
            }
        }
    }
}
