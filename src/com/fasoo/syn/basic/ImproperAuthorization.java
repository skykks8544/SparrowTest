package com.fasoo.syn.basic;

import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Writer: Date: 4/24/12
 */
public class ImproperAuthorization {

    public Map<String, String> getEnv() {
        Map<String, String> envMap = new HashMap<String, String>();
        // Do someething ...
        return envMap;
    }

    public void authorize(String sSingleId, int iFlag, String sServiceProvider, String sUid, String sPwd) {
        Map<String, String> env = System.getenv();
        Map env2 = getEnv();
        String noneString = "none";
        String authentication = Context.SECURITY_AUTHENTICATION;
        // do something
        env.put(Context.INITIAL_CONTEXT_FACTORY, "an_factory");
        env.put(Context.PROVIDER_URL, sServiceProvider);
        // 익명으로 LDAP 인증을 사용
        env.put(javax.naming.Context.SECURITY_AUTHENTICATION, "none"); /* alarm */
        env2.put(Context.SECURITY_AUTHENTICATION, noneString); /* alarm */
        env.put(authentication, "none"); /* alarm */
        System.getenv().put(authentication, "none"); /* alarm */
        env2.put(Context.SECURITY_AUTHENTICATION, "safe");
        env.put(Context.SECURITY_PRINCIPAL, sUid);
        env.put(Context.SECURITY_CREDENTIALS, sPwd);
        // ...
    }

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;

        data = "passwd";

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String msgId = request.getParameter("msgId");
        if (username == null || password == null || !KrdUtil.isAuthenticatedUser(username, password)) {
            throw new Exception("Invalid username, password");
        }
        if (msgId == null) {
            throw new Exception("Invalid msgId");
        }
        KrdMessage msg = KrdUtil.LookupKrdMessageObject(msgId);
        /* FLAW */
        if (msg != null) {
            response.getWriter().println("From: " + msg.getUserName()); /* BUG */
            response.getWriter().println("Subject: " + msg.getSubField()); /* BUG */
            response.getWriter().println("Body: " + msg.getBodyField()); /* BUG */
        }

        if (msg != null && msg.getUserName().equals(username)) {
            response.getWriter().println("From: " + msg.getUserName());
            response.getWriter().println("Subject: " + msg.getSubField());
            response.getWriter().println("Body: " + msg.getBodyField());
        }

        KrdMessage msg2 = KrdUtil.LookupKrdMessageObject(msgId + "1");
        if (msg != null && msg2 != null && msg2.getUserName().equals(username)) {
            response.getWriter().println("From: " + msg.getUserName()); /* BUG */
            response.getWriter().println("Subject: " + msg.getSubField()); /* BUG */
            response.getWriter().println("Body: " + msg.getBodyField()); /* BUG */
        }
    }
}

class KrdUtil {

    public static boolean isAuthenticatedUser(String username, String password) {
        // TODO Auto-generated method stub
        return false;
    }

    public static KrdMessage LookupKrdMessageObject(String msgId) {
        // TODO Auto-generated method stub
        return null;
    }

}

class KrdMessage {
    private String userName; /* BUG */
    private String subField; /* BUG */
    private String BodyField; /* BUG */

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSubField() {
        return subField;
    }

    public void setSubField(String subField) {
        this.subField = subField;
    }

    public String getBodyField() {
        return BodyField;
    }

    public void setBodyField(String bodyField) {
        BodyField = bodyField;
    }
}
