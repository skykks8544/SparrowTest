package com.fasoo.sem;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import java.io.FileReader;

public class RESOURCE_INJECTION_TestCase {
    public void test(HttpServletRequest request) throws IOException {
        int def = 1000;
        FileReader fileReader;

        // 외부에서 입력한 데이터를 받는다.
        String service = request.getParameter("Service No"); // tainted

        if (service != null) {
            fileReader = new FileReader(service); /* BUG 2 */
        } else {
            fileReader = new FileReader("default");
        }
    }

    public void safeTest1(HttpServletRequest request) throws IOException {
        // 외부에서 입력한 데이터를 받는다.
        String service = request.getParameter("Service No"); // tainted

        // indexOf 의 반환값이 -1 과 동등 연산자에 의해서 비교 될 경우
        if (service.indexOf("&") != -1) { // "service" is filtered by "indexOf" method of J_FilterIO in "if" condition. Assumed safe
            FileReader fileReader = new FileReader(service); /* SAFE 2 */
        }
    }
    public void safeTest2(HttpServletRequest request) throws IOException {
        // 외부에서 입력한 데이터를 받는다.
        String service = request.getParameter("Service No"); // tainted

        // indexOf("<") 에 의해서 filter 되는 경우
        if (service.indexOf("<") != -1) { // "service" is filtered by "indexOf" method of J_FilterIO in "if" condition. Assumed safe
            FileReader fileReader = new FileReader(service); /* SAFE 2 */
        }
    }
}
