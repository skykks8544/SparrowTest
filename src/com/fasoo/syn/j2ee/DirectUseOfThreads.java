package com.fasoo.syn.j2ee;

import javax.servlet.http.HttpServlet;

public class DirectUseOfThreads extends HttpServlet {

    public void doPost() {
        Foo foo = new Foo();
        new Thread(foo).run(); /* Bug */
    }

    public void doGet() {
        Foo foo = new Foo();
        new Thread(foo).run(); /* Bug */
    }

    public void service() {
        Foo foo = new Foo();
        new Thread(foo).run(); /* Bug */
    }

    class Foo extends Thread {

    }
}
