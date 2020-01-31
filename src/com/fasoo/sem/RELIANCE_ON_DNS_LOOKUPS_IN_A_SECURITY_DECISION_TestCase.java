package com.fasoo.sem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.net.InetAddress;

/**
 * RELIANCE_ON_DNS_LOOKUPS_IN_A_SECURITY_DECISION test case

 * Date: 12. 12. 24.
 * Time: 오후 12:33
 */
public class RELIANCE_ON_DNS_LOOKUPS_IN_A_SECURITY_DECISION_TestCase {
    // source : request.getRemoteAddr(), sink : contains()
    public void test1(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        InetAddress addr = InetAddress.getByName(request.getRemoteAddr());

        if(addr.getCanonicalHostName().contains("trustme.com")){ // disabled

        }
    }
    // source : request.getRemoteAddr(), sink : endsWith()
    public void test2(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        InetAddress addr = InetAddress.getByName(request.getRemoteAddr());
        if(addr.getCanonicalHostName().endsWith("trustme.com")){ /* BUG */

        }
    }
    // source : request.getRemoteAddr(), sink : equals()
    public void test3(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        InetAddress addr = InetAddress.getByName(request.getRemoteAddr());
        if(addr.getCanonicalHostName().equals("trustme.com")){ /* BUG */

        }
    }
    // source : request.getRemoteAddr(), sink : equalsIgnoreCase()
    public void test4(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        InetAddress addr = InetAddress.getByName(request.getRemoteAddr());
        if(addr.getCanonicalHostName().equalsIgnoreCase("trustme.com")){ /* BUG */

        }
    }
    // source : request.getRemoteAddr(), sink : matches()
    public void test5(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        InetAddress addr = InetAddress.getByName(request.getRemoteAddr());
        if(addr.getCanonicalHostName().matches("trustme.com")){ // disabled

        }
    }
    // source : request.getRemoteAddr(), sink : startsWith()
    public void test6(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        InetAddress addr = InetAddress.getByName(request.getRemoteAddr());
        if(addr.getCanonicalHostName().startsWith("trustme.com")){ /* BUG */

        }
    }
    // source : request.getRemoteAddr(), sink : startsWith(java.lang.String, int)
    public void test7(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        InetAddress addr = InetAddress.getByName(request.getRemoteAddr());

        if(addr.getCanonicalHostName().startsWith("trustme.com", 3)){ /* BUG */

        }
    }
    // source : request.getParameter(), sink : endsWith()
    public void test8(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        InetAddress addr = InetAddress.getByName(request.getParameter("domain"));

        if(addr.getCanonicalHostName().endsWith("trustme.com")){ /* BUG */

        }
    }
    // source : reader.readLine(), sink : endsWith()
    public void test9(HttpServletRequest request, BufferedReader reader) throws javax.servlet.ServletException, java.io.IOException {
        InetAddress addr = InetAddress.getByName(reader.readLine());

        if(addr.getCanonicalHostName().endsWith("trustme.com")){ /* BUG */

        }
    }
    // source : request.getRemoteAddr(), sink : endsWith(java.lang.String, int)
    public void test10(HttpServletRequest request) throws javax.servlet.ServletException, java.io.IOException {
        InetAddress addr = InetAddress.getByName(request.getRemoteAddr());
        if(addr.getHostName().endsWith("trustme.com")){ /* BUG */

        }
    }
    // source : invalid, sink : endsWith(java.lang.String, int)
    public void test11(HttpServletRequest request) throws javax.servlet.ServletException, java.io.IOException {
        String p = System.getProperty("some.key");
        if(p != null && p.endsWith("trustme.com")){

        }
    }
}
