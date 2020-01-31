/* IDS00-J. Sanitize untrusted data passed across a trust boundary */
/* SQL_INJECTION */
package cert;

import javax.servlet.http.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;

/*
    SQL_INJECTION checker 를 검증하기 위한 test case
 */
/* OPT : -show_followers */

public class IDS00_J_2 extends HttpServlet {
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    @SuppressWarnings("unchecked")
    public IDS00_J_2(String password) {
        super();
        Connection con = null;
        try {
            // 1. Driver를 로딩한다.
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // 2. Connection 얻어오기
            con = DriverManager.getConnection("jdbc:oracle:thin:@占쎄쑴�좑옙�깍폒占쏙옙1521:ora9", "john", password);
            // 3. Statement 얻기 --> 쿼리문 작성하여 적용하기 위한 용도            
            stmt = con.createStatement();
        } catch (ClassNotFoundException e) {} catch (SQLException e) {}
        finally {
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
            String query = makeQuery(1, name);
            doTransaction(query);  //@violation SQL_INJECTION

            conn.prepareStatement(query);  //@violation SQL_INJECTION
            stmt.execute(query);  //@violation SQL_INJECTION
            stmt.executeQuery(query);  //@violation SQL_INJECTION
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }
    
    @SuppressWarnings("unchecked")
    public void doPostSafe(HttpServletRequest req, HttpServletResponse res) {
        try {
            String name = req.getParameter("name"); // tainted input has come
            String query = makeQuery(2, name);
            doTransaction(query); /* SAFE */ 

            conn.prepareStatement(query); /* SAFE */ 
            stmt.execute(query); /* SAFE */ 
            stmt.executeQuery(query); /* SAFE */
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }
    private void doTransaction(String query) throws SQLException {
        stmt.execute(query);
    }

    private String makeQuery(int type, String name) {
    	if (type == 1) {
    		return "select * from account where name = '" + name + "'"; 
    	}
    	else {
    		return "select * from account";
    	}
    }

}
