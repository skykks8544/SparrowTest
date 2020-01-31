package com.fasoo.sem.helper.jcert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 SQL_INJECTION checker 를 검증하기 위한 test case
 */
/* OPT : -show_followers */
public class SQL_INJECTION_TestCase extends HttpServlet {

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    @SuppressWarnings("unchecked")
    public SQL_INJECTION_TestCase(String password) {
        super();
        Connection con = null;
        try {
            // 1. Driver를 로딩한다.
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // 2. Connection 얻어오기
            con = DriverManager.getConnection("jdbc:oracle:thin:@아이피주소:1521:ora9", "john", password);
            // 3. Statement 얻기 --> 쿼리문 작성하여 적용하기 위한 용도
            stmt = con.createStatement();
        } catch (ClassNotFoundException e) {
        } catch (SQLException e) {
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void doPost(HttpServletRequest req, HttpServletResponse res) {
        try {
            String name = req.getParameter("name"); // tainted input has come
            String query = "select * from account where name = '" + name + "'";
            doTransaction(query); /* Bug */

            conn.prepareStatement(query); /* Bug */
            stmt.execute(query); /* Bug */
            stmt.executeQuery(query); /* Bug */
            stmt.executeUpdate(query); /* Bug */
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }

    private void doTransaction(String query) throws SQLException {
        stmt.execute(query);
    }
}
