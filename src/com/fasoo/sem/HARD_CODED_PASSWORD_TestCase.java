package com.fasoo.sem;

import java.sql.*;
import java.io.IOException;

public class HARD_CODED_PASSWORD_TestCase {
    public static <T> T identity(T x) {
        return x;
    }
    public void test() throws IOException, SQLException {
        Connection con = null;
        String pass = "wlsalstlr";
        try {
            con = DriverManager.getConnection("data-url", "root", pass); 		/* BUG */
        } catch (Exception e) {

        } finally {
            if (con != null) {
                con.close();
            }
        }
    }
    public void test2() throws IOException, SQLException {
        Connection con = null;
        String pass = identity("wlsalstlr");
        try {
            con = DriverManager.getConnection("data-url", "root", pass); 		/* BUG */
        } catch (Exception e) {

        } finally {
            if (con != null) {
                con.close();
            }
        }
    }
}
