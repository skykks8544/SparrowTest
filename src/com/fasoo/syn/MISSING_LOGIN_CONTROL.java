package com.fasoo.syn;

public class MISSING_LOGIN_CONTROL {

    public void badLogin() {
        login(); /* BUG */
    }

    public void goodLogin() {
        for (int i = 0; i < 10; i++) {
            login();
        }
    }

    private void login() {

    }
}
