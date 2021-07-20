package com.company.crm.commons;

/**
 * @author conrad
 * @date 2021/07/07
 */
public class SynchronizedDem {
    public void method() {
        synchronized (this) {
            System.out.println("synchronized 代码块");
        }
    }
}
