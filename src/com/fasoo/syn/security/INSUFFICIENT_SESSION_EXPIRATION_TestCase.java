package com.fasoo.syn.security;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

public class INSUFFICIENT_SESSION_EXPIRATION_TestCase extends HttpServlet {
    public void noExpiration(HttpSession session) {
        int timeout = -2;

        if (session.isNew()) {
            session.setMaxInactiveInterval(-1); /* BUG */
        }

        if (session.isNew()) {
            session.setMaxInactiveInterval(timeout); /* BUG */
        }

        int sessionTimeout = timeout;
        if (session.isNew()) {
            session.setMaxInactiveInterval(sessionTimeout); /* BUG */
        }

    }
}