package com.springapp.mvc;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * User:
 * Date:
 * Time:
 */
@Service
public class UserDaoImpl implements UserDao {
    @Override
    public void setUserName(int id, String name) {
        String lowerName = name.toLowerCase();
    }

    @Override
    public void doQuery(String queryParam) {
        String query = "select * from account where name = '" + queryParam + "'";

        try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@1.1.1.1:1521:ora9", "john", "1234");
            Statement stmt = conn.createStatement();
            stmt.executeQuery(query);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void doQuery2(String queryParam) {
        String query = "select * from account where name = '" + queryParam + "'";

        try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@1.1.1.1:1521:ora9", "john", "1234");
            Statement stmt = conn.createStatement();
            stmt.executeQuery(query);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
