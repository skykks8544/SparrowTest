package com.fasoo.sem;

import java.io.*;
import javax.servlet.http.HttpServletRequest;

/**
 * 디렉토리 경로 조작 을 테스트 해 볼수 있는 Test case
 * 상대경로 조작, 절대경로 조작 Test case 가 차례로 열거 되어 있다.
 */
public class PathTraversalTest {
    public void pathTraversalRelativeTest(HttpServletRequest request) {
        String name = request.getParameter("filename");
        if (name != null) {
            File file = new File("/usr/local/tmp/" + name); /* BUG */
            file.delete();
        }
    }

    public void pathTraversalRelativeSafeTest(HttpServletRequest request) {
        String name = request.getParameter("filename");
        if (name != null) {
            String sanitizedName = name.replaceAll("/","");
            File file = new File("/usr/local/tmp/" + sanitizedName);
            file.delete();
        }
    }

    public void pathTraversalRelativeTest2(HttpServletRequest request) throws IOException {
        String name = request.getParameter("filename");
        if (name != null) {
            FileOutputStream outputStream = new FileOutputStream("/usr/local/tmp/" + name); /* BUG */
            outputStream.write(1);
        }
    }

    public void pathTraversalAbsTest(HttpServletRequest request) {
        String name = request.getParameter("filename");
        if (name != null) {
            FileInputStream file = null;
            try {
                file = new FileInputStream("/usr/local/tmp/" + name); /* BUG */
                file.read();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void pathTraversalAbsSafeTest(HttpServletRequest request) {
        String name = request.getParameter("filename");
        if (name != null) {
            name = name.replaceAll("/","");
            FileInputStream file = null;
            try {
                file = new FileInputStream("/usr/local/tmp/" + name); /* SAFE */
                file.read();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
