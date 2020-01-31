package com.fasoo.sem;

/**
 * Category: CC (Code Correctness)
 * Test: Infinite Recursive Call
 * Writer: 
 * Date: 9/11/12
 */
public class INFINITE_RECURSIVE_CALL {
    public void bar(int a) {
        if (a > 0) {

        } else {
            if (a > 1) {
                bar(a - 1); /* NOT BUG */
            }
        }
    }
    public void fac2(int a) {
        int b = a;
    }
    public void fac2(String a) {
        fac2(Integer.parseInt(a)); /* NOT BUG */
    }
    public int factorial(int n) {
        return n * factorial(n - 1); /* BUG */
    }
    public static int fac(int n) {
        if(n > 0) {
            System.out.println("asdf");
        } else {
            System.out.println("qwer");
        }
        return n * fac(n - 1); /* BUG */
    }
    public void fac3(int n) {
        if (n > 0) {
            fac3(n-1); /* NOT BUG */
        }
    }
    public void safeFactorial(int n) {
        if (n == 1) { // 함수 안에 if 문이 있으면 재귀 함수 호출이라고 해도 무시
            int a = 1;
        } else {
            safeFactorial(n - 1); /* NOT BUG */
            int b = 2;
        }
    }
    public int foo(int a) {
        int b = -1;
        if (a > 0) {
            b = 1;
        } else {
            b = 3;
        }
        return foo(b); /* BUG */
    }
}
