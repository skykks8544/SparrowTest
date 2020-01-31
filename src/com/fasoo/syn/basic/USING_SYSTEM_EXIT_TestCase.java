package com.fasoo.syn.basic;

import javax.servlet.http.HttpServlet;

public class USING_SYSTEM_EXIT_TestCase extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet() {
        System.exit(1);			/* Bug */
    }
}
