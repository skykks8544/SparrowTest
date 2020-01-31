package com.fasoo.syn;

public class NonThreadSafeSingleton {
    private static Foo foo = null;

    // multiple simultaneous callers may see partially initialized objects
    public static Foo getFoo() {
        if (foo == null)
            foo = new Foo();
        return foo;
    }
}

class Foo {

}
