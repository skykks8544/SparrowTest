/* IDS02-J. Canonicalize path names before validating them */
/* PATH_TRAVERSAL */
package cert;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Properties;

public class IDS02_J {
   
    private String sanify(int type, String path) {
    	if (type > 0) {
    		return "/app/upload/bad";
    	}
    	else {
    		return path;
    	}
    }
    
    private FileInputStream createFile(String path) throws FileNotFoundException {
    	return new FileInputStream("/usr/local/tmp/" + path);
    }
    
    public void pathTraversalComplex(Properties request) {
    	String name = request.getProperty("filename");
    	String taint = sanify(0, name);
    	FileInputStream file = null;
        try {
            file = createFile(taint);	//@violation PATH_TRAVERSAL 
            file.read();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(file != null) file.close();
            } catch (IOException e) {}
        }
    }
    
    public void pathTraversalComplexSafe(Properties request) {
    	String name = request.getProperty("filename");
    	String taint = sanify(1, name);
    	FileInputStream file = null;
        try {
            file = createFile(taint); /* SAFE */ 
            file.read();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(file != null) file.close();
            } catch (IOException e) {}
        }
    }

}
