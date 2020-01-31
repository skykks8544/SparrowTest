package com.fasoo.syn.basic;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PASSWORD_MANAGEMENT_PASSWORD_IN_REDIRECT_TestCase {
    private String pw = "passwd";

    public void redirect(ServletRequest request,HttpServletResponse response) throws Exception{
        String usr = request.getParameter("username");
        String pw = request.getParameter("passwd");

        response.sendRedirect("test.jsp?j_uname=" + usr + "&j_pwd=" + pw);						/* Bug */
        pw = request.getParameter(this.pw);
        response.sendRedirect("test.jsp?j_uname=" + usr + "&j_pwd=" + pw);						/* Bug */
        response.sendRedirect("test.jsp?j_uname=" + usr + "&j_pwd=" + request.getParameter("passwd"));		/* Bug */
        response.sendRedirect("test.jsp?j_uname=" + usr + "&j_pwd=" + request.getParameter(this.pw));			/* Bug */
        response.encodeRedirectURL("test.jsp?j_uname=" + usr + "&j_pwd=" + pw);
    }
}
