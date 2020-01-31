package com.fasoo.sem;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;

public class EXTERNAL_CONTROL_OF_SYSTEM_OR_CONFIGURATION_SETTING {
    public void foo(HttpServletRequest request) {
        try {
            InitialContext ctx = new InitialContext();
            DataSource dataSource = (DataSource) ctx.lookup("jdbc:ocl:orcl");
            Connection con = dataSource.getConnection();

            // catalog 정보는 외부로부터 유입되는 정보
            String catalog = request.getParameter("catalog");
            // catalog 정보를 DB Connection 을 위해서 해당 값을 체크하지 않고, DB 카탈로그 정보에 지정함
            con.setCatalog(catalog); /* BUG */
            con.close();
        } catch (NamingException e) {
        } catch (SQLException e) {
        }
    }

    public String filter(String str) {
        return str;
    }

    int getSomeInt() {
        return 2;
    }

    String getCatalogData(HttpServletRequest request) {
        return request.getParameter("catalog");
    }

    public void testComplicated(HttpServletRequest request) {
        try {
            InitialContext ctx = new InitialContext();
            DataSource dataSource = (DataSource) ctx.lookup("jdbc:ocl:orcl");
            Connection con = dataSource.getConnection();

            String catalog;
            if (getSomeInt() > 1) {
                // catalog 정보는 외부로부터 유입되는 정보
                catalog = getCatalogData(request);
            } else {
                catalog = "safe";
            }

            // catalog 정보를 DB Connection 을 위해서 해당 값을 체크하지 않고, DB 카탈로그 정보에 지정함
            con.setCatalog(catalog); /* BUG */
            con.close();
        } catch (NamingException e) {
        } catch (SQLException e) {
        }
    }
}
