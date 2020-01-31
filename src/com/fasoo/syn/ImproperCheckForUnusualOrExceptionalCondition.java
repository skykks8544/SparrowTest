package com.fasoo.syn;

import java.io.File;
import java.io.FileReader;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Writer: 
 * Date: 3/29/12
 */
public class ImproperCheckForUnusualOrExceptionalCondition extends HttpServlet {
    public static final String ITEM_NAME = "ITEM_NAME";
    public static final String IMPORTANT_ITEM = "IMPORTANT_ITEM";

    public void readFromFile(String fileName) {
        try {
            File file = new File(fileName);
            FileReader reader = new FileReader(file);

            // 아래 alarm 은 MethodIgnoresReturnValueChecker 에서 검증한다.
            reader.read();
            // do something

        }
        // 아래와 같이 광범위한 Exception 사용을 검출한다.
        catch (Exception e) { /* ALARM */
            System.out.println("error");
        }
    }
}
