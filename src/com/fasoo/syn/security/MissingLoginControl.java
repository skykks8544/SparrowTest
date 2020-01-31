package com.fasoo.syn.security;

public class MissingLoginControl {

    private void login() {
        for(int i=0; i<10; i++) {
            // Log-in here ...
        }
    }

    private void anotherlogin() {
        int i = 0;
        while(i < 10) {
            // Log-in here ...
        }
    }

    private void another2login() {
        int i = 0;
        do {
            // Log-in here ...
        } while(i < 10);
    }

    private void another3login() {
        for(;;) { /* BUG */
            // Log-in here ...
        }
    }

    private void another4login() {
        int i = 0;
        while(((true))) { /* BUG */
            // Log-in here ...
            i++;
        }
    }

    public void func1() {
        login(); /* BUG */
    }

    public void func2() {
        for(int i=0; i<10; i++) {
            login();
        }
    }

    public void func3() {
        int i = 0;
        while(i < 10) {
            login();
            i++;
        }
    }

    public void func4() {
        for(int i=0; true; i++) {
            login(); /* BUG */
        }
    }

    public void func5() {
        int i = 0;
        while(((true))) {
            login(); /* BUG */
            i++;
        }
    }

    public void func6() {
        do {
            login(); /* BUG */
        } while(true);
    }
}
