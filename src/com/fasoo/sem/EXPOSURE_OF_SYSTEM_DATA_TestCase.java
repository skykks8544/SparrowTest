package com.fasoo.sem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CWE-497 test case<br/>
 * ERR01-J. Do not allow exceptions to expose sensitive information<br/>
 */
public class EXPOSURE_OF_SYSTEM_DATA_TestCase {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String path = System.getenv("PATH");

        System.out.println("Not in path: " + path); /* BUG 3 */
    }
}
