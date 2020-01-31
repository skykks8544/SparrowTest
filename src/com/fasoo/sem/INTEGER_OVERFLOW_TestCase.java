package com.fasoo.sem;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class INTEGER_OVERFLOW_TestCase {
    public void test(HttpServletRequest request) throws IOException {

        String version = request.getParameter("dir_type");
        String taintedIndex = request.getParameter("index");

        int iVersion = Integer.parseInt(version);
        int index = Integer.parseInt(taintedIndex);

        int[] arr = new int[10];
        int a = arr[iVersion]; /* BUG */// Access

        int b = arr[index]; /* BUG */
    }

    public void test2(HttpServletRequest request) throws IOException {

        String version = request.getParameter("dir_type");
        String taintedIndex = request.getParameter("index");

        int iVersion = Integer.parseInt(version);
        int size = Integer.parseInt(taintedIndex);

        int[] arr = new int[10];
        int[] arr2 = new int[iVersion]; /* BUG */// Create
        int[] arr3 = new int[size]; /* BUG */
    }

    public void safeTest(HttpServletRequest request) throws IOException {

        String version = request.getParameter("dir_type");
        int iVersion = Integer.parseInt(version);

        int[] arr = new int[10];

        if (iVersion < 0) {
            return;
        }
        int a = arr[iVersion]; // Safe
    }

    public void safeTest2(HttpServletRequest request) throws IOException {

        String version = request.getParameter("dir_type");
        int iVersion = Integer.parseInt(version);

        int[] arr = new int[10];

        if (iVersion >= 0) {
            int a = arr[iVersion]; // Safe
        }
    }

    public void safeTest3(HttpServletRequest request) throws IOException {

        String version = request.getParameter("dir_type");
        int iVersion = Integer.parseInt(version);

        int[] arr = new int[10];

        if (iVersion < 99999) {
            int a = arr[iVersion]; // Safe
        }
    }

    public void arithmeticTest() {
        short data = (short) (new java.security.SecureRandom()).nextInt(1 + Short.MAX_VALUE); /* BUG */// dangerous
                                                                                                        // downcast
        multiplication(data); /* BUG */
    }

    public void multiplication(int x) {
        int result = x * x;
        System.out.println(result);
    }
}
