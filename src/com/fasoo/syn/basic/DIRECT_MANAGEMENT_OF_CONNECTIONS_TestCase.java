package com.fasoo.syn.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DIRECT_MANAGEMENT_OF_CONNECTIONS_TestCase {
    public void doGet() {
        try {
            Connection conn = DriverManager.getConnection("localhost");/* BUG */
            System.out.println(conn);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}

