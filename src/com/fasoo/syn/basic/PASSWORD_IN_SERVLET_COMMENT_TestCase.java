package com.fasoo.syn.basic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PASSWORD_IN_SERVLET_COMMENT_TestCase {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println("<!--DB username = joe, DB password = 123-->"); /* BUG */
    }
}
