package com.fasoo.syn.basic;

import javax.servlet.ServletRequest;

public class PASSWORD_MANAGEMENT_HEAP_INSPECTION_TestCase {
    private String pw = "password";

    public void passwordManagementHeapInspection(ServletRequest request) throws Exception{
        String pw = request.getParameter(this.pw);
        String var = "var string.";

        if(pw == null || pw.contains("<")){
            System.out.println("Bad input.");
        }else{
            String hpw = new String(pw);		/* Bug */
            hpw = new String(pw.toLowerCase() + var);
            hpw = new String(pw + ":" + var);	/* Bug */
            System.out.println(hpw);
        }
    }
}
