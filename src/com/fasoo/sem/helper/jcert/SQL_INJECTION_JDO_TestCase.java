package com.fasoo.sem.helper.jcert;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import com.fasoo.sem.helper.Contact;
import com.fasoo.sem.helper.ContactDAO;

/* OPT : -show_followers */
public abstract class SQL_INJECTION_JDO_TestCase implements ContactDAO {
    @SuppressWarnings("unchecked")
    public List<Contact> listContacts() {
        PersistenceManager pm = getPersistenceManagerFactory().getPersistenceManager();/* Bug */// ND
        String query = "select from " + Contact.class.getName();
        Properties props = null;
        String fileName = null;
        FileInputStream in = null;
        try {
            props = new Properties();
            fileName = "contacts.txt";
            in = new FileInputStream(fileName);
            if (in != null) {
                props.load(in);
            }
            in.close();
            // 외부로부터 입력을 받는다.
            String name = props.getProperty("name"); /*
                                                     * tainted input, name comes
                                                     * in
                                                     */
            if (name != null) {
                query += " where name = '" + name + "'"; /* tainted input, query */
            }
        } catch (IOException e) {

        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // 외부 입력값이 JDO 객체의 인자로 사용된다.
        return (List<Contact>) pm.newQuery(query).execute(); /* Bug */
    }

    @SuppressWarnings("unchecked")
    public List<Contact> listContacts2() {
        PersistenceManager pm = getPersistenceManagerFactory().getPersistenceManager(); /* Bug */// ND
        String query = "select from " + Contact.class.getName();
        Properties props = null;
        String fileName = null;
        FileInputStream in = null;
        try {
            props = new Properties();
            fileName = "contacts.txt";
            in = new FileInputStream(fileName);
            if (in != null) {
                props.load(in);
            }
            // 외부로부터 입력을 받는다.
            String name = props.getProperty("name"); /*
                                                     * tainted input, name comes
                                                     * in
                                                     */
            if (name != null) {
                query += " where name = '" + name + "'"; /* tainted input, query */
            }
        } catch (IOException e) {

        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        String[] splittedQuery = query.split(".");
        String subQuery = splittedQuery[0].substring(1);

        // 외부 입력값이 JDO 객체의 인자로 사용된다.
        return (List<Contact>) pm.newQuery(subQuery).execute(); /* Bug */
    }

    public PersistenceManagerFactory getPersistenceManagerFactory() {
        return null;
    }
}
