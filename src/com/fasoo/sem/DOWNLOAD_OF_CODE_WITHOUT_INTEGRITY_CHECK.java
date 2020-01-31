package com.fasoo.sem;

import java.net.URL;
import java.net.URLClassLoader;

import javax.servlet.http.HttpServletRequest;

public class DOWNLOAD_OF_CODE_WITHOUT_INTEGRITY_CHECK {

    void test(HttpServletRequest req) throws Exception {
        URL[] classURLs = new URL[] { new URL(req.getParameter("file")) };
        URLClassLoader loader = new URLClassLoader(classURLs);
        Class loadedClass = Class.forName("LoadMe", true, loader);
        String str = (String) loadedClass.newInstance(); /* BUG */
    }

    void safeTest(HttpServletRequest req) throws Exception {
        URL[] classURLs = new URL[] { new URL(req.getParameter("file")) };
        URLClassLoader loader = new URLClassLoader(classURLs);
        Class loadedClass = Class.forName("LoadMe", true, loader);
        if (loadedClass.getName() != loadedClass.getSimpleName())
            return;
        String str = (String) loadedClass.newInstance(); /* SAFE */
    }

    void testComplicated() throws Exception {
        URL[] classURLs = new URL[] { new URL("") };
        URLClassLoader loader = new URLClassLoader(classURLs);
        Class loadedClass = null;
        if (getSomeInt() > 0) { // getSomeInt returns -1, so go to false branch
            loadedClass = this.getClass();
        } else {
            loadedClass = Class.forName("LoadMe", true, loader); // tainted
        }

        String str = (String) loadedClass.newInstance(); /* BUG */
    }

    int getSomeInt() {
        return -1;
    }
}
