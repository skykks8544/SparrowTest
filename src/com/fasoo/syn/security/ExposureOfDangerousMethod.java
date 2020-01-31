package com.fasoo.syn.security;

public class ExposureOfDangerousMethod {

    public void dangerousMethod() { /* BUG */
        // Do something dangerous things ...
    }

    private void privateCall() {
        // ...
        dangerousMethod();
    }

    public void publicCall() {
        // ...
        dangerousMethod(); /* BUG */
    }
}
