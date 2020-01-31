package com.fasoo.syn;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;


public class UseOfInsufficientRandomValues{
    static Random gr = new Random();

    public void func1(){
        Calendar beginDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();

        Random r1 = new Random();
        Random r2 = new Random();
        Random r3 = new Random();
        Random r4;
        Random r5 = new Random();

        int seed = 10;

        r2.setSeed(seed + 2);
        r3.setSeed(new Date().getTime());
        gr.setSeed(new Date().getTime());
        r4 = r3;

        int v;
        v = (int)(Math.random() % 10); /* BUG: Math.random() used */
        v = r1.nextInt() % 10; /* BUG: Not seeded */
        v = r2.nextInt() % 10; /* BUG: Seeded but a constant has been used */
        v = r3.nextInt() % 10;
        v = r4.nextInt() % 10;
        v = (int)(((r5.nextInt() >>> 1) % (endDate.getTimeInMillis() - beginDate.getTimeInMillis() + 1)) + beginDate.getTimeInMillis());
        v = gr.nextInt() % 10;
    }

    static {
        int v = gr.nextInt() % 10; /* BUG: Not seeded */
    }

    public void func2() {
        int v = gr.nextInt() % 10; /* BUG: Not seeded */
    }
}
