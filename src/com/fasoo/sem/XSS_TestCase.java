package com.fasoo.sem;

import javax.servlet.http.*;
import java.util.StringTokenizer;
import java.io.*;

public class XSS_TestCase {
    public void test(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String data = "";
        StringTokenizer st = new StringTokenizer(request.getQueryString(), "&");
        while (st.hasMoreTokens())
        {
            String token = st.nextToken(); /* a token will be like "id=foo" */
            if(token.startsWith("id="))   /* check if we have the "id" parameter" */
            {
                data = token.substring(3); /* set data to "foo" */
                break; /* exit while loop */
            }
        }
        if(data != null) {
            response.getWriter().println("<br>bad(): data = " + data); /* BUG */
        }
    }
    public void test2(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String data = "";
        StringTokenizer st = new StringTokenizer(request.getQueryString(), "&");
        while (st.hasMoreTokens())
        {
            String token = st.nextToken(); /* a token will be like "id=foo" */
            if(token.startsWith("id="))   /* check if we have the "id" parameter" */
            {
                data = token.substring(3); /* set data to "foo" */
                break; /* exit while loop */
            }
        }
        if(data != null) {
            PrintWriter w = new PrintWriter(new FileOutputStream("temp"));
            w.println("<br>bad(): data = " + data);
            w.close();
        }
    }
}
