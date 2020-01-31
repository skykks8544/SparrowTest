package com.fasoo.syn.basic;

import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class UseOfInsufficientRandomValues {
    static Random gr = new Random();

    public void func1() {
        Calendar beginDate = new CalendarChild();
        Calendar endDate = new CalendarChild();

        Random r1 = new Random();
        Random r2 = new Random();
        Random r3 = new Random();
        Random r4;
        Random r5 = new Random();
        Random r6 = new Random(System.currentTimeMillis());

        int seed = 10;

        r2.setSeed(seed + 2);
        r3.setSeed(new Date().getTime());
        gr.setSeed(new Date().getTime());
        r4 = r3;

        int v;
        double random_d = Math.random() % 10; /* BUG: Math.random() used */
        v = r1.nextInt() % 10; /* BUG: Not seeded */
        v = r2.nextInt() % 10; /* BUG: Seeded but a constant has been used */
        v = r3.nextInt() % 10;
        v = r4.nextInt() % 10;
        long random_l = ((r5.nextInt() >>> 1) % (endDate.getTimeInMillis() - beginDate.getTimeInMillis() + 1)) + beginDate.getTimeInMillis();
        v = gr.nextInt() % 10;
        v = r6.nextInt() % 10; /* Safe: Seeded in Random Constructor */
    }

    static {
        int v = gr.nextInt() % 10; /* BUG: Not seeded */
    }

    public void func2() {
        int v = gr.nextInt() % 10; /* BUG: Not seeded */
    }

    public void testSecureRandom() {
        SecureRandom random = new SecureRandom();
        System.out.println("Random int: " + random.nextInt(100)); // SAFE
    }

    class CalendarChild extends Calendar {

        @Override
        protected void computeTime() {
            // To change body of implemented methods use File | Settings | File
            // Templates.
        }

        @Override
        protected void computeFields() {
            // To change body of implemented methods use File | Settings | File
            // Templates.
        }

        @Override
        public void add(int field, int amount) {
            // To change body of implemented methods use File | Settings | File
            // Templates.
        }

        @Override
        public void roll(int field, boolean up) {
            // To change body of implemented methods use File | Settings | File
            // Templates.
        }

        @Override
        public int getMinimum(int field) {
            return 0; // To change body of implemented methods use File |
                        // Settings | File Templates.
        }

        @Override
        public int getMaximum(int field) {
            return 0; // To change body of implemented methods use File |
                        // Settings | File Templates.
        }

        @Override
        public int getGreatestMinimum(int field) {
            return 0; // To change body of implemented methods use File |
                        // Settings | File Templates.
        }

        @Override
        public int getLeastMaximum(int field) {
            return 0; // To change body of implemented methods use File |
                        // Settings | File Templates.
        }
    }
}