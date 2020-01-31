package com.fasoo.sem;

import javax.servlet.http.*;

public class SENSITIVE_COOKIE_WITHOUT_SECURE_ATTRIBUTE_TestCase {
    void bad(HttpServletRequest request, HttpServletResponse response) {
        Cookie c = new Cookie("secret message","test");
        if(request.isSecure()) {
            response.addCookie(c); /* BUG */
        }
    }
    void good(HttpServletRequest request, HttpServletResponse response) {
        Cookie c = new Cookie("secret message","test");
        if(request.isSecure()) {
            c.setSecure(true);
            response.addCookie(c);
        }
    }
}
