package com.fasoo.syn;

import java.io.IOException;

public class ExposureOfSystemData {
    public void f(Throwable t) {
        try {
            g();
        } catch (IOException e) {
            e.printStackTrace(); /* BUG */
            System.err.printf(e.getMessage()); /* BUG */
            System.err.println(e.getMessage()); /* BUG */
            System.err.println(e); /* BUG */
        }
        t.printStackTrace(); /* BUG */
    }

    private void g() throws IOException {

    }
}