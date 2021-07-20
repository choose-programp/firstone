package test;

/**
 * @author conrad
 * @date 2021/07/08
 */
public class Test2 {
    private final static Object LOCK = new Object();

    public void startThreadA() {
        new Thread(() -> {
            synchronized (LOCK) {
                System.out.println(Thread.currentThread().getName() + ": get lock");
                //启动线程b
                startThreadB();
                System.out.println(Thread.currentThread().getName() + ": start wait");
                try {
                    //线程a wait
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ": get lock after wait");
                System.out.println(Thread.currentThread().getName() + ": release lock");
            }
        }, "thread-A").start();
    }

    private void startThreadB() {
        new Thread(() -> {
            synchronized (LOCK) {
                System.out.println(Thread.currentThread().getName() + ": get lock");
                //启动线程c
                startThreadC();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ": start notify");
                //线程b唤醒其他线程
                LOCK.notify();
                System.out.println(Thread.currentThread().getName() + ": release lock");
            }
        }, "thread-B").start();
    }

    private void startThreadC() {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ": thread c start");
            synchronized (LOCK) {
                System.out.println(Thread.currentThread().getName() + ": get lock");
                System.out.println(Thread.currentThread().getName() + ": release lock");
            }
        }, "thread-C").start();
    }

    public static void main(String[] args) {
        new Test2().startThreadA();
    }
}
