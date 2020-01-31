package com.fasoo.sem;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import egovframework.com.cmm.EgovWebUtil;

public class XSS_ERROR_MESSAGE_TestCase {

    public void getErrorMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name"); // tainted

        response.sendError(1, name); /* Bug */
    }

    public void getErrorMessageSafe(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name"); // tainted

        String safeString = EgovWebUtil.clearXSSMinimum(name);

        response.sendError(1, safeString); /* Safe */
    }
}