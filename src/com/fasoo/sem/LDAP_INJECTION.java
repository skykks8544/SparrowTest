package com.fasoo.sem;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.servlet.http.HttpServletRequest;

public class LDAP_INJECTION {

    @SuppressWarnings("unchecked")
    public void test(HttpServletRequest request) {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:389/o=rootDir");
        try {
            javax.naming.directory.DirContext ctx = new InitialDirContext(env);

            // LDAP Search 를 하기 위해 name 을 읽는다.
            String name = request.getParameter("name");
            String filter = "(name = " + name + ")";
            // LDAP search 가 name 값에 대한 여과없이 그대로 통과되어 검색이 되어진다.
            NamingEnumeration answer = ctx.search("ou=NewHires", filter, new SearchControls()); /* BUG */
            printSearchEnumeration(answer);
            ctx.close();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    private void printSearchEnumeration(NamingEnumeration answer) {
    }

    int getAnotherInt() {
        return -1;
    }

    String getFilter(String name) {
        return "(name = " + name + ")";
    }

    @SuppressWarnings("unchecked")
    public void testComplicated(HttpServletRequest request) {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:389/o=rootDir");
        try {
            javax.naming.directory.DirContext ctx = new InitialDirContext(env);

            // LDAP Search 를 하기 위해 name 을 읽는다.
            String name = request.getParameter("name"); // tainted
            String filter = getFilter(name); // tainted value go to getFilter
            if (getAnotherInt() < 0) {
                ctx.search("ou=NewHires", filter, new SearchControls()); /* BUG */
            } else {
                ctx.search("ou=NewHires", "defs", new SearchControls());
            }

            ctx.close();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
