package com.fasoo.syn;

import javax.servlet.http.HttpServlet;

public class LeftoverDebugCode extends HttpServlet{
    public static void main(String[] a){ /* BUG */
        if(a!=null && a[0] instanceof String)
            System.out.println(a.length);
    }
}

class LeftoverDebugCode1 extends HttpServlet{
    public static void main(String []a){ /* BUG */
    }
}

class LeftoverDebugCode2 extends HttpServlet{
    public static void main(String a[]){ /* BUG */
    }
}

class LeftoverDebugCode3{
    public static void main(String[] a){
    }
}

class LeftoverDebugCode4 extends HttpServlet{
    public static final void main(String[] a){
    }
}

class LeftoverDebugCode5 extends HttpServlet{
    public static void main(String a){
    }
}
