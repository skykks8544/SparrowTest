package android;

import java.io.File;
import java.util.Properties;

public class AbsolutePath {
    public void ussafe(String fileName){
        Properties properties = new Properties();
        String name = properties.getProperty(fileName);
        File file = new File("/usr/local/tmp/" + name);     /* Bug */
        file.delete();
    }

    public void safe(String fileName){

        Properties properties = new Properties();
        String name = properties.getProperty(fileName);

        if(name.indexOf("/") < 0){
            File file = new File("/usr/local/tmp/" + name); /* Safe */
            if(file != null){
                file.delete();
            }
        }
    }
}
