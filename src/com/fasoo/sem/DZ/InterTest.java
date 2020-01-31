package com.fasoo.sem.DZ;

public class InterTest {
    void foo(int p) {
        int x = 0;

        bar(x); /* BUG */

        if(p == 0) {
            System.out.println("dummy");
        }

        bar(p); /* BUG */
    }
    void bar(int x) {
        int z = 10 / x;
        System.out.println("dummy");
    }
    /* TODO: fix below to be reported */
    void bar() {
        int x = 20;
        int y = 30;
        double ratio = getRatio(x, y);
    }

    public double getRatio(int a, int b) {
        int c = a + b + 100;

        return c / (a - b + 10);
    }
}
