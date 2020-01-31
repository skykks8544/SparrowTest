package com.fasoo.sem.ND;

public class ArgInter {
    void arg3(String x) {
        x.toString();
    }
    void arg2(String x) {
        arg3(x);
    }
    void arg1(String x) {
        arg2(x);
    }
    void trig() {
        String x = null;
        arg1(x); /* BUG */
    }
}
