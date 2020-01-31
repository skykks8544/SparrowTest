import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class XSS_ERROR_MESSAGE {
    public void func(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String env = System.getenv("ADD");
        response.sendError(404, "bad() ? Parameter name has value " + env);
    }
}
