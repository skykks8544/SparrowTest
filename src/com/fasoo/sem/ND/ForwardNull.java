package com.fasoo.sem.ND;

public class ForwardNull {

    public void test() {
        String upperCased = toUpperCase(null); // Forward Null, Null Dereference
        String lowserCased = toLowerCase("abcdefg"); // Not bug, It`s ok
    }

    public String toUpperCase(String arg) {
        return arg.toUpperCase();
    }

    public String toLowerCase(String arg) {
        return arg.toLowerCase();
    }
}
