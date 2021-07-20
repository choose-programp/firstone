package test;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author conrad
 * @date 2021/07/18
 */
public class Test10 {
    private static final ReentrantReadWriteLock readWriteLock  = new ReentrantReadWriteLock();
    private static final ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    private static final ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
    public static void main(String[] args) {
        new Thread(()->{
            read();
        },"r1").start();
        new Thread(()->{
            read();
        },"r2").start();
        new Thread(()->{
            read();
        },"r3").start();

        new Thread(()->{
            write();
        },"w1").start();

        new Thread(()->{
            write();
        },"w2").start();
    }


    public static void read(){
        try {
            readLock.lock();
            System.out.println(Thread.currentThread()+"读操作");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread()+"读操作结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }


    public static void write(){
        try {
            writeLock.lock();
            System.out.println(Thread.currentThread()+"写操作");
            Thread.sleep(3000);
            System.out.println(Thread.currentThread()+"写操作结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }
}
