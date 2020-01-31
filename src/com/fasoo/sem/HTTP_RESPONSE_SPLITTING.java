package com.fasoo.sem;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HTTP_RESPONSE_SPLITTING extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        // 사용자의 입력정보를 받아서 쿠키를 생성한다.
        String author = request.getParameter("authorName");
        Cookie cookie = new Cookie("replidedAuthor", author); /* BUG */
        cookie.setMaxAge(1000);
        // cookie.setMaxAge(1000);
        // cooke.setSecure(true); // HTTP로 서비스하는 경우 쿠키 값 설정 안됨(위험)
        // HTTP 서비스만 제공하는 경우 사용
        // …
        // 생성된 쿠키를 브라우저에 전송해 저장하도록 한다.
        response.addCookie(cookie);
        RequestDispatcher frd = request.getRequestDispatcher("cookieTest.jsp");
        frd.forward(request, response);

        String field = request.getParameter("field");
        String fileName = java.net.URLEncoder.encode(field, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\""); /* BUG */
    }

    int getSomeInt() {return 2;}
    public void doPostComplicated(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String field = request.getParameter("field");
        String fileName;
        if (getSomeInt() > 2) {
            fileName = java.net.URLEncoder.encode("safe", "UTF-8");
        } else {
            fileName = java.net.URLEncoder.encode(field, "UTF-8"); // tainted
        }

        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\""); /* BUG */
    }
}
