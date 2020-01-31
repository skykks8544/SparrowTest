package com.fasoo.sem;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Writer: 
 * Date: 9/18/12
 */
/* OPT : -show_followers */
public class RELIANCE_ON_UNTRUSTED_INPUTS_IN_A_SECURITY_DECISION_TestCase {
    public void test(HttpServletRequest request) {
        String userRole = null;
        Cookie[] cookies = request.getCookies(); /* TAINTED */
        for (int i = 0; i < cookies.length; i++) {
            Cookie c = cookies[i];
            if (c.getName().equals("role")) { /* BUG */
                userRole = c.getValue();
            }
        }
    }

    public void test2(HttpServletRequest request) {
        String userRole = null;
        Cookie[] cookies = request.getCookies(); /* TAINTED */
        for (int i = 0; i < cookies.length; i++) {
            Cookie c = cookies[i];
            if (Boolean.TRUE.equals(c.getValue())) { /* BUG */

            }
        }
    }
}
