package com.fasoo.syn.basic;

public class DO_NOT_USE_NOTIFY_TestCase {
    public synchronized void notifyJob() {
        boolean flag = true;
        notify(); /* ALARM */
    }
}
