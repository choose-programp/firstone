package test;

import java.util.HashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author conrad
 * @date 2021/07/09
 */
public class Test4 {

    private static AtomicInteger nextHashCode =
            new AtomicInteger();

    private static final int HASH_INCREMENT = 0x61c88647;

    public static void main(String[] args) throws InterruptedException {
        new HashMap<>();
        AtomicInteger atomicInteger = new AtomicInteger();

        Lock lock = new ReentrantLock();
        Condition c1 = lock.newCondition();

        Semaphore semaphore = new Semaphore(10);
        semaphore.acquire();

        ThreadLocal threadLocal = new ThreadLocal();
        Thread thread = new Thread();

        final int threadLocalHashCode = nextHashCode();
        System.out.println(threadLocalHashCode);

        int a = 10;
        int b = 6;
        a-=b++;
        System.out.println(b + "  "+a);
        System.out.println((15 << 5) - 15);

    }

    private static int nextHashCode() {
        return nextHashCode.getAndAdd(HASH_INCREMENT);
    }

}
