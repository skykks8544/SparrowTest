package com.fasoo.syn;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class SensitiveCookieInHttpsSessionWithoutSecureAttribute{
    final String ACCOUNT_ID="account";

    private Cookie cookie1=new Cookie(ACCOUNT_ID, "accountId");

    public void setup(ServletRequest request, HttpServletResponse response){
        String accountID=request.getParameter("accountID");
        Cookie cookie2 = new Cookie(ACCOUNT_ID, accountID);
        Cookie cookie3 = new Cookie(ACCOUNT_ID, accountID);
        Cookie cookie4 = null;
        boolean flag = true;

        cookie2.setSecure(true);
        cookie3.setSecure(flag);

        response.addCookie(cookie1); /* BUG */
        response.addCookie(cookie2);
        response.addCookie(cookie3);
        response.addCookie(cookie4);
        response.addCookie(new Cookie(ACCOUNT_ID, accountID)); /* BUG */
    }

    public Cookie getc(){
        return new Cookie("", "");
    }
}
