package com.fasoo.syn.basic;

/**
 * Writer: 
 * Date: 4/9/12
 */
public class NONSYNCHRONIZED_METHOD_OVERRIDES_SYNCHRONIZED_METHOD_TestCase {
    class U9627 {
        public synchronized void synchronizedMethod() {
            for (int i = 0; i < 10; i++) System.out.println(i);
        }
    }

    class Foo extends U9627 {
        public void synchronizedMethod() { /* ALARM */
            for (int i = 0; i < 10; i++) System.out.println(i);
        }
    }
}
