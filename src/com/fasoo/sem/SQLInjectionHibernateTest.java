package com.fasoo.sem;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class SQLInjectionHibernateTest {

    @SuppressWarnings("deprecation")
    public void listHistory(HttpServletRequest request) {
        Session session = new Configuration().configure().buildSessionFactory().openSession();

        String str = "abcd";

        // 외부로부터 입력을 받음
        String idValue = request.getParameter("idLow");
        // 외부 입력을 검증없이 SQL query 문의 인자로 사용한다.
        Query query1 = session.createQuery("from Address a where a.name='" + idValue); /* BUG */
        Query query2 = session.createQuery("from Address a where a.name='" + str); /* SAFE */
        query1.list();
        query2.list();

    }
}
