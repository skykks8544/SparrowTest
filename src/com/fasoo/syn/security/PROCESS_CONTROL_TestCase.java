package com.fasoo.syn.security;

public class PROCESS_CONTROL_TestCase {

    public void test() {
        System.loadLibrary("test.dll"); /* BUG */
    }

    /** TODO **/
    public void test2() {
        String libName = "test.dll"; /* BUG */
        System.loadLibrary(libName);
    }
}
