package com.fasoo.syn.security;

public class MissingPasswordRecoveryControl {

    private void recoverPassword() {
        for(int i=0; i<10; i++) {
            // Recover password here ...
        }
    }

    private void anotherrecoverPassword() {
        int i = 0;
        while(i < 10) {
            // Recover password here ...
        }
    }

    private void another2recoverPassword() {
        int i = 0;
        do {
            // Recover password here ...
        } while(i < 10);
    }

    private void another3recoverPassword() {
        for(;;) { /* BUG */
            // Recover password here ...
        }
    }

    private void another4recoverPassword() {
        int i = 0;
        while(((true))) { /* BUG */
            // Recover password here ...
            i++;
        }
    }

    public void func1() {
        recoverPassword(); /* BUG */
    }

    public void func2() {
        for(int i=0; i<10; i++) {
            recoverPassword();
        }
    }

    public void func3() {
        int i = 0;
        while(i < 10) {
            recoverPassword();
            i++;
        }
    }

    public void func4() {
        for(int i=0; true; i++) {
            recoverPassword(); /* BUG */
        }
    }

    public void func5() {
        int i = 0;
        while(((true))) {
            recoverPassword(); /* BUG */
            i++;
        }
    }

    public void func6() {
        do {
            recoverPassword(); /* BUG */
        } while(true);
    }
}
