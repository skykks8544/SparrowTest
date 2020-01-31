package com.fasoo.sem;

import com.fasoo.sem.helper.EntityManagerImpl;

import java.io.FileInputStream;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServletRequest;

/**
 * SQL Injection Persistence test case
  */
public class SQLInjectionPersistenceTest implements ServletContextListener {
    @SuppressWarnings("unchecked")
    public List<?> getAllItemsInWildcardCollection(HttpServletRequest request) {
        EntityManager em = getEntityManager();
        List<SQLInjectionPersistenceTest> r_type = null;
        try {
            
            // 외부로 부터 입력을 받는다. tainted
            String id = request.getParameter("id");
            /*
                외부 입력 값이 query 의 인자로 사용이 된다.
                javax.persistence.Query, javax.persistence.EntityManager.createNativeQuery
             */
            Query query = em.createNativeQuery("SELECT OBJECT(i) FROM Item i WHERE i.itemID>" + id); /* BUG */
            List<SQLInjectionPersistenceTest> items = query.getResultList();
            // ...
            return r_type;
        } catch (Exception e) {
            return null;
        }
    }
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {}
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {}

    private EntityManager getEntityManager() {
        return new EntityManagerImpl();
    }
}
