/* IDS07-J. Do not pass untrusted, unsanitized data to the Runtime.exec() method */
/* COMMAND_INJECTION */
package cert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.lang.Runtime;


/* OPT : -show_followers */
public class IDS07_J {
    public void test() throws IOException {
        Properties props = new Properties();
        String fileName = "file_list";
        FileInputStream in = new FileInputStream(fileName);
        props.load(in); /* BUG */ // Resource Leak
        in.close();
        String version = props.getProperty("dir_type");
        String cmd = new String("cmd.exe /K \"rmanDB.bat \"");
        Runtime.getRuntime().exec(cmd + " c:\\prog_cmd\\" + version); /* BUG */ //@violation COMMAND_INJECTION 

        String [] env = {"OS=Windows", "JVM=1.6"};
        Runtime.getRuntime().exec("abc-" + version + ".exe", env); /* BUG */ //@violation COMMAND_INJECTION
    }
    
    String taintString(int type, Properties props) {
        if (type > 0)
            return props.getProperty("dir_type");
        else
            return "abc";
    }
    
    private String getDirectoryType(int type, Properties props) {
    	if (type > 3) {
    		return "abs";
    	}
    	else {
    		return props.getProperty("dir_type");
    	}
    }
    
    public void testComplicated() throws IOException {
        Properties props = new Properties();
        String fileName = "file_list";
        FileInputStream in = new FileInputStream(fileName);
        props.load(in); 
        in.close();

        String version;

        version = getDirectoryType(1, props);
        String cmd = new String("cmd.exe /K \"rmanDB.bat \"");
        Runtime.getRuntime().exec(cmd + " c:\\prog_cmd\\" + version); /* BUG */ //@violation COMMAND_INJECTION

        String tail = taintString(1, props);
        Runtime.getRuntime().exec(cmd + " c:\\prog_cmd\\" + tail); /* BUG */ //@violation COMMAND_INJECTION
    }
    
    public void testComplicatedSafe() throws IOException {
        Properties props = new Properties();
        String fileName = "file_list";
        FileInputStream in = new FileInputStream(fileName);
        props.load(in); 
        in.close();

        String version;

        version = getDirectoryType(5, props);
        String cmd = new String("cmd.exe /K \"rmanDB.bat \"");
        Runtime.getRuntime().exec(cmd + " c:\\prog_cmd\\" + version); /* SAFE */

        String tail = taintString(0, props);
        Runtime.getRuntime().exec(cmd + " c:\\prog_cmd\\" + tail); /* SAFE */ 
    }
}
