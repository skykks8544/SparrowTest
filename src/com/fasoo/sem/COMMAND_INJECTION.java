package com.fasoo.sem;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.Runtime;
import javax.servlet.http.HttpServletRequest;

public class COMMAND_INJECTION {
    public void test(HttpServletRequest request) throws IOException {
        String version = request.getParameter("version");				// tainted
        String cmd = new String("cmd.exe /K \"rmanDB.bat \"");
        Runtime.getRuntime().exec(cmd + " c:\\prog_cmd\\" + version); /* BUG */
    }

    int getSomeInt() {return 2;}
	
    String taintString(HttpServletRequest request) {
        if (getSomeInt() > 1)
            return request.getParameter("version2");
        else
            return "abc";
    }
	
	public void testComplicated(HttpServletRequest request) throws IOException {       

        String version;

        if (getSomeInt() > 3) {
            version = "abc";
        } else {
            version = request.getParameter("version");				// tainted
        }

        String cmd = new String("cmd.exe /K \"rmanDB.bat \"");
        Runtime.getRuntime().exec(cmd + " c:\\prog_cmd\\" + version); /* BUG */

        String tail = taintString(request);
        Runtime.getRuntime().exec(cmd + " c:\\prog_cmd\\" + tail); /* BUG */
    }
}
