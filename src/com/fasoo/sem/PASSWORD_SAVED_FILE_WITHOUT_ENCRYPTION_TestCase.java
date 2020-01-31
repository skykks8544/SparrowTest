package com.fasoo.sem;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

/**
 * Writer: 
 * Date: 9/18/12
 */

public class PASSWORD_SAVED_FILE_WITHOUT_ENCRYPTION_TestCase {
    public void test () {
        try {
            Socket socket = new Socket("taranis", 4444); // TAINTED
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String password = getPassword();
            out.write(password); /* BUG */
            out.close();
            socket.close();
        } catch (FileNotFoundException e) {
        } catch (UnknownHostException e) {
        } catch (IOException e) {
        }
    }

    public void test2 (HttpServletRequest request) {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        PreparedStatement p = null;

        try {
            // ...
            if (userName == null || password == null || !isAuthenticatedUser(userName,password)) {
                throw new Exception("인증에러");
            }
            p = conn.prepareStatement("INSERT INTO employees VALUES(?,?)");
            p.setString(1, userName); /* BUG */
            p.setString(2, password); /* BUG */
            p.execute();
        } catch (SQLException e) {
        } catch (Exception e) {
        }
    }

    public void test3 (String url, String name) {
        try {
            FileInputStream in = new FileInputStream("file");
            byte [] pass = new byte[8];
            in.read(pass); // TAINTED

            conn = DriverManager.getConnection(url, name, new String(pass)); /* BUG */
        } catch (FileNotFoundException e) {
        } catch (SQLException e) {
        } catch (IOException e) {
        }
    }

    public void safeTest() {
        try {
            Socket socket = new Socket("taranis", 4444); // TAINTED
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String password = getPassword();
            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");

            String encryptedPassword = new String(c.update(password.getBytes()), 0, 10);
            out.write(encryptedPassword, 0, encryptedPassword.length()); /* SAFE */
            out.close();
            socket.close();
        } catch (FileNotFoundException e) {
        } catch (UnknownHostException e) {
        } catch (IOException e) {
        } catch (NoSuchAlgorithmException e) {
        } catch (NoSuchPaddingException e) {
        }
    }
    private boolean isAuthenticatedUser(String userName, String password) {
        return true;
    }
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    @SuppressWarnings("unchecked")
    public PASSWORD_SAVED_FILE_WITHOUT_ENCRYPTION_TestCase() {
        super();
        try {
            // 1. Driver를 로딩한다.
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // 2. Connection 얻어오기
            conn = DriverManager.getConnection("jdbc:oracle:thin:@아이피주소:1521:ora9", "john", "oracle");
            // 3. Statement 얻기 --> 쿼리문 작성하여 적용하기 위한 용도
            stmt = conn.createStatement();
        } catch (ClassNotFoundException e) {} catch (SQLException e) {}
    }
    public String getPassword() {return "1234";}
}
