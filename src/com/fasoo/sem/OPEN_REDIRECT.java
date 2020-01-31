package com.fasoo.sem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Writer: 
 * Date: 7/19/12
 */
public class OPEN_REDIRECT extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getQueryString();
        if (query.contains("url")) {
            String url = request.getParameter("url");
            response.sendRedirect(url); /* BUG */

            url = url.replaceAll("\r", "");
            response.sendRedirect(url); /* NOT BUG */
        }
    }

    int getAnotherInt() {return 2;}
    int getSomeInt() {return getAnotherInt() - 1;};
    String getMaliciousURL(String input) {
        return input + "/attacker/bankaccount/send_money=?ok";
    }
    protected void doGetComplicated(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getQueryString();
        if (query.contains("url")) {
            String url;

            if (getSomeInt() > 0) { // go to true branch
                url = getMaliciousURL(request.getParameter("url"));
            } else {
                url = "http://safe.com";
            }
            response.sendRedirect(url); /* BUG */

            url = url.replaceAll("\r", "");
            response.sendRedirect(url); /* NOT BUG */
        }
    }
}
