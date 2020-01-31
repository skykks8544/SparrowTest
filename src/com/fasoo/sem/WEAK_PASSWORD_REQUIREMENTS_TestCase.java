package com.fasoo.sem;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;

public class WEAK_PASSWORD_REQUIREMENTS_TestCase {
    public void test(HttpServletRequest request, String db_password) {
        Connection con = null;
        Statement stmt = null;
        try {
            // 1. Driver를 로딩한다.
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // 2. Connection 얻어오기
            con = DriverManager.getConnection("jdbc:oracle:thin:@아이피주소:1521:ora9", "john", db_password);
            // 3. Statement 얻기 --> 쿼리문 작성하여 적용하기 위한 용도
            stmt = con.createStatement();

            String userName = request.getParameter("username");
            String password = request.getParameter("password");
            String query = "blah blah" + password + "blah";

            // SQL_INJECTION, WEAK_PASSWORD_REQUIREMENTS
            stmt.execute(query); /* BUG 2 */

        } catch (ClassNotFoundException e) {
        } catch (SQLException e) {
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    public void test2(HttpServletRequest request, String db_password) {
        Connection con = null;
        Statement stmt = null;
        try {
            // 1. Driver를 로딩한다.
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // 2. Connection 얻어오기
            con = DriverManager.getConnection("jdbc:oracle:thin:@아이피주소:1521:ora9", "john", db_password);
            // 3. Statement 얻기 --> 쿼리문 작성하여 적용하기 위한 용도
            stmt = con.createStatement();

            String userName = request.getParameter("username");
            String password = request.getParameter("password");

            if (password.matches("") || password.indexOf("@!#") < 0 || password.length() < 9)
                return;

            String query = "blah blah" + password + "blah";

            // SQL_INJECTION
            stmt.execute(query); /* BUG */

        } catch (ClassNotFoundException e) {
        } catch (SQLException e) {
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    public void test3(HttpServletRequest request, String db_password) {
        Connection con = null;
        Statement stmt = null;
        try {
            // 1. Driver를 로딩한다.
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // 2. Connection 얻어오기
            con = DriverManager.getConnection("jdbc:oracle:thin:@아이피주소:1521:ora9", "john", db_password);
            // 3. Statement 얻기 --> 쿼리문 작성하여 적용하기 위한 용도
            stmt = con.createStatement();

            String userName = request.getParameter("username");
            String password = request.getParameter("password");

            String query = "blah blah" + userName + "blah";

            // SQL_INJECTION
            stmt.execute(query); /* BUG */

        } catch (ClassNotFoundException e) {
        } catch (SQLException e) {
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    private String getData(HttpServletRequest request) {
        return request.getParameter("passwd");
    }

    public void test4(HttpServletRequest request, String db_password) {
        Connection con = null;
        Statement stmt = null;
        try {
            // 1. Driver를 로딩한다.
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // 2. Connection 얻어오기
            con = DriverManager.getConnection("jdbc:oracle:thin:@아이피주소:1521:ora9", "john", db_password);
            // 3. Statement 얻기 --> 쿼리문 작성하여 적용하기 위한 용도
            stmt = con.createStatement();

            String userName = request.getParameter("username");
            String passwd = getData(request);

            String query = "blah blah" + passwd + "blah";

            // SQL_INJECTION, WEAK_PASSWORD_REQUIREMENTS
            stmt.execute(query); /* BUG 2 */

        } catch (ClassNotFoundException e) {
        } catch (SQLException e) {
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
            }
        }
    }
}