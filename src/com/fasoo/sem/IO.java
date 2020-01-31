package com.fasoo.sem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: starblood
 * Date: 12. 11. 22.
 * Time: 오후 1:49
 * To change this template use File | Settings | File Templates.
 */
public class IO {
    private static final String dbUrl = "";
    private static final String dbUsername = "";
    private static final String dbPassword = "";

    public static boolean static_t = true;
    public static boolean static_f = false;
    public static final Logger logger = new Logger();

    public static boolean static_returns_t() {return true;}
    public static boolean static_returns_f() {return false;}
    static class Logger {
        public void log(int level, String message, Object obj) {

        }
    }
    /* use this method to get a database connection for use in SQL
     * Injection and other test cases that use a database.
     */
    public static Connection getDBConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    }
}
