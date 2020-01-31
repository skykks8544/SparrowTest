package com.fasoo.sem;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.servlet.http.HttpServletRequest;

import com.fasoo.sem.helper.Contact;
import com.fasoo.sem.helper.ContactDAO;

public abstract class SQLInjectionJDOTest implements ContactDAO {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unchecked")
    public List<Contact> listContacts(HttpServletRequest request) {
        PersistenceManager pm = getPersistenceManagerFactory().getPersistenceManager();/* BUG */// ND
        String query = "select from " + Contact.class.getName();

        // 외부로부터 입력을 받는다.
        // tainted input, name comes in
        String name = request.getParameter("name");
        if (name != null) {
            // tainted input, query
            query += " where name = '" + name + "'";
        }

        // 외부 입력값이 JDO 객체의 인자로 사용된다.
        return (List<Contact>) pm.newQuery(query).execute(); /* BUG */
    }

    public PersistenceManagerFactory getPersistenceManagerFactory() {
        return null;
    }
}
