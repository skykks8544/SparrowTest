import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Writer: 
 * Date: 5/29/12
 *
 * CLEARTEXT_TRANSMISSION_OF_SENSITIVE_INFORMATION test case
 */
public class CLEAR_TEXT_TRANSMISSION_OF_SENSITIVE_INFORMATION_TestCase {
    String getPassword() {
        return "secret";
    }

    void foo() {
        try {
            Socket socket = new Socket("taranis", 4444);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String password = getPassword();
            out.write(password); /* BUG */
        } catch (FileNotFoundException e) {
            // ...
        } catch (UnknownHostException e) {
            // ...
        } catch (IOException e) {
            // ...
        }
    } /* BUG */ // Resource Leak

    public int getSomeInt() { return -1;}
    public int getSomeInt2() { return 1;}

    void testComplicated(int a) {
        try {
            Socket socket = new Socket("taranis", 4444);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String password;
            if (a > 10) {
                password = getPassword() + "abcdefg";
            } else {
                password = getPassword();
            }
            password = password + "tiger";
            if (getSomeInt() > 0) {
                password = null;
            }
            out.write(password); /* BUG */
            if (getSomeInt2() > 0) {
                password = getPassword();
                out.write(password); /* BUG */
            }
            if (getSomeInt2() > 1) {
                password = getPassword();
                out.write(password); /* SAFE */
            }
        } catch (FileNotFoundException e) {
            // ...
        } catch (UnknownHostException e) {
            // ...
        } catch (IOException e) {
            // ...
        }
    } /* BUG */ // Resource Leak
}
