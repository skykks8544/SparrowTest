package com.fasoo.syn.basic;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LeftoverDebugCode extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public static void main(String[] a) { /* BUG */
        if (a != null && a[0] instanceof String)
            System.out.println(a.length);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("debug") != null) {
            System.out.println("debug code here."); /* BUG */
        }

        if (request.getParameter("debug") != null) {
            System.out.println("debug code here."); /* BUG */
        }

        if (request.getParameter("debug") != null)
            System.out.println("debug code here."); /* BUG */

        if (request.getParameter("debug") != null)
            System.out.println("debug code here."); /* BUG */

        if (request.getParameter("debug") != null) {
            System.out.println("debug code here."); /* BUG */
            int a = 10;
        }
    }
}

class LeftoverDebugCode1 extends HttpServlet {
    public static void main(String[] a) { /* BUG */
    }
}

class LeftoverDebugCode2 extends HttpServlet {
    public static void main(String a[]) { /* BUG */
    }
}

class LeftoverDebugCode3 {
    public static void main(String[] a) { /* BUG */
    }
}

class LeftoverDebugCode4 extends HttpServlet {
    public static final void main(String[] a) {
    }
}

class LeftoverDebugCode5 extends HttpServlet {
    public static void main(String a) {
    }
}
