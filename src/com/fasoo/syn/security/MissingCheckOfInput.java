package com.fasoo.syn.security;

public class MissingCheckOfInput {

    private int globalInput;
    private int uncheckedGlobalInput;

    public MissingCheckOfInput() {
        if((globalInput + 20) > 10) { // Input checking
            // Deal with exception here ...
        }
    }
    private void print(int value) {
        // Do something ...
    }

    private boolean check(int value) {
        // Do something ...
        return value > 10 ? true : false;
    }

    public void method(int input, int uncheckedInput) {
        print(uncheckedInput); /* BUG */
        if(input > 10) {
            // Deal with exception here ...
            return;
        }
        int tmp = input;
        int tmp2 = tmp;
        print(input);
        print(tmp2);
        print(globalInput);
        print(uncheckedGlobalInput); /* BUG */
        if(check(uncheckedInput)) {
            print(uncheckedInput); /* SAFE */
        }
    }

    public void another(int input, int uncheckedInput) {
        print(input); /* BUG */
        print(input > 0 ? input : null); /* SAFE */
    }

    public void other(int input, int uncheckedInput) {
        if(input > 10) {
            // Deal with exception here ...
            return;
        }
        int k = input;
        print(k);
        k = uncheckedInput; /* BUG */
        print(k); /* BUG */
    }
}
