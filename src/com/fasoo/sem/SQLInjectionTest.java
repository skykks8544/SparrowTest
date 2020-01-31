package com.fasoo.sem;

import javax.servlet.http.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;

/*
    SQL_INJECTION checker 를 검증하기 위한 test case
 */
public class SQLInjectionTest extends HttpServlet {
    private Connection con;
    private Statement stmt;
    private ResultSet rs;
    @SuppressWarnings("unchecked")
    public SQLInjectionTest() {
        super();
        try {
            // 1. Driver를 로딩한다.
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // 2. Connection 얻어오기
            con = DriverManager.getConnection("jdbc:oracle:thin:@아이피주소:1521:ora9", "john", "oracle");
            // 3. Statement 얻기 --> 쿼리문 작성하여 적용하기 위한 용도
            stmt = con.createStatement();
        } catch (ClassNotFoundException e) {} catch (SQLException e) {}
    }
    @SuppressWarnings("unchecked")
    public void doPost(HttpServletRequest req, HttpServletResponse res) {
        try {
            String name = req.getParameter("name"); // tainted input has come
            String query = "select * from account where name = '" + name + "'";
            doTransaction(query); /* BUG */
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }
    private void doTransaction(String query) throws SQLException {
        stmt.execute(query);
    }
}
