package com.fasoo.sem;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;

/**
 * Writer: 
 * Date: 7/30/12
 */
public class PersistentCookieTest {
    public void makeCookie(ServletRequest request) {
        String maxAge = request.getParameter("maxAge");
        if (maxAge.matches("[0-9]+")) {
            String sessionID = request.getParameter("sessionID");
            if (sessionID.matches("[A-Z=0-9a-z]+")) {
                Cookie c = new Cookie("sessionID", sessionID); /* BUG */ // HTTP_RESPONSE_SPLITTING
                // 외부 입력이 쿠키 유효한 설정에 그대로 사용 되었다.
                c.setMaxAge(Integer.parseInt(maxAge)); /* BUG */
            }
        }
    }

    public void makeCookie2(ServletRequest request) {
        String maxAge = request.getParameter("maxAge");
        if (maxAge.matches("[0-9]+")) {
            String sessionID = request.getParameter("sessionID");
            if (sessionID.matches("[A-Z=0-9a-z]+")) {
                Cookie c = new Cookie("sessionID", sessionID); /* BUG */ // HTTP_RESPONSE_SPLITTING
                // 외부 입력이 쿠키 유효한 설정에 그대로 사용 되었다.
                int t = Integer.parseInt(maxAge);
                if (t > 7200) { // 쿠기의 시간을 재설정
                    t = 7200;
                }
                c.setMaxAge(t); /* BUG */
            }
        }
    }

    public void makeSafeCookie(ServletRequest request) {
        String maxAge = request.getParameter("maxAge");
        if (maxAge.matches("[0-9]+")) {
            String sessionID = request.getParameter("sessionID");
            if (sessionID.matches("[A-Z=0-9a-z]+")) {
                Cookie c = new Cookie("sessionID", sessionID); /* BUG */ // HTTP_RESPONSE_SPLITTING
                // 외부 입력이 쿠키 유효한 설정에 그대로 사용 되었다.
                int t = Integer.parseInt(maxAge);
                if (t > 3600) { // 쿠기의 시간을 재설정
                    t = 3600;
                }
                c.setMaxAge(t); /* SAFE */
            }
        }
    }

    public void makeSafeCookie2(ServletRequest request) {
        String maxAge = request.getParameter("maxAge");
        if (maxAge.matches("[0-9]+")) {
            String sessionID = request.getParameter("sessionID");
            if (sessionID.matches("[A-Z=0-9a-z]+")) {
                Cookie c = new Cookie("sessionID", sessionID); /* BUG */ // HTTP_RESPONSE_SPLITTING
                // 외부 입력이 쿠키 유효한 설정에 그대로 사용 되었다.
                int t = Integer.parseInt(maxAge);
                if (t > 60) { // 쿠기의 시간을 재설정
                    t = 60;
                }
                c.setMaxAge(t); /* SAFE */
            }
        }
    }
}
