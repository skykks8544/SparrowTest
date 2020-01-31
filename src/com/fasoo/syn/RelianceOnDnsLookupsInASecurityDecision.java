package com.fasoo.syn;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;

public class RelianceOnDnsLookupsInASecurityDecision extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws javax.servlet.ServletException, java.io.IOException{
        boolean trusted=false;
        InetAddress addr=InetAddress.getByName(req.getRemoteAddr());
        String hostname1=addr.getHostName();
        String hostname2=InetAddress.getByName(req.getRemoteAddr()).getHostName();

        if(InetAddress.getByName(req.getRemoteAddr()).getCanonicalHostName().startsWith("trustme.com")){ // Violation
            trusted=true;
        }else if(addr.getCanonicalHostName().endsWith("trustme.com")){ // Violation
            trusted=true;
        }else if(hostname1.equalsIgnoreCase("trustme.com")){ // Violation
            trusted=true;
        }else if(addr.getHostAddress().equals("127.0.0.1")){
            trusted=true;
        }

        if("trustme.com".startsWith(InetAddress.getByName(req.getRemoteAddr()).getCanonicalHostName())){ // Violation
            trusted=true;
        }else if("trustme.com".endsWith(addr.getCanonicalHostName())){ // Violation
            trusted=true;
        }else if("trustme.com".equalsIgnoreCase(hostname2)){ // Violation
            trusted=true;
        }else if("127.0.0.1".equals(addr.getHostAddress())){
            trusted=true;
        }

        if(trusted){
            System.out.println("trusted true.");
        }else{
            System.out.println("trusted false.");
        }

        String ip = req.getRemoteAddr();
        if(ip == null || "".equals(ip)) {
            return;
        }

        String trustedAddr = "127.0.0.1";

        if(ip.equals(trustedAddr)) {
            // Do something ...
        } else {
            // Do something ...
        }
    }
}
