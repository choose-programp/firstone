package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author conrad
 * @date 2021/07/14
 */
public class Test5 {
    private static final int THREAD_LOOP_SIZE = 2;

    private static ThreadLocal<String> threadLocal1 = new ThreadLocal<>();
    private static ThreadLocal<String> threadLocal2 = new ThreadLocal<>();
    private static ThreadLocal<String> threadLocal3 = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_LOOP_SIZE);

        for (int i = 0; i < THREAD_LOOP_SIZE; i++) {
            executorService.execute(() -> {
                threadLocal1.set("123");
                threadLocal2.set("234");
                threadLocal3.set("345");
            });
        }

        Thread.sleep(1000);

        System.out.println("123");

        executorService.shutdown();
    }

}
