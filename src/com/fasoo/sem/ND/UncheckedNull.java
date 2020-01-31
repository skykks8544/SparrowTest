package com.fasoo.sem.ND;

public class UncheckedNull {
    void foo(String x) {
        int i = 0;
        System.out.println("dummy");

        if(x != null) {
            int j = 1;
            int k = 2;
            int l = 3;
            System.out.println("another dummy");
            String t=x.toUpperCase();
        }

        int m = 4;
        int n = 5;
        String t= x.toString(); /* BUG */

        System.out.println("final dummy");

        return;
    }
}
