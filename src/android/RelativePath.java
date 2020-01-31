package android;

import java.io.File;
import java.util.Properties;

public class RelativePath {
    public void unsafe(Properties request, String fileName){
        String name = request.getProperty(fileName);
        if(name != null){
            File file = new File("/usr/local/tmp/" + name);     /* Bug */
            file.delete();
        }
    }

    public void safe(Properties request, String fileName){
        String name = request.getProperty(fileName);
        if(name != null){
            name = name.replaceAll("/", "");
            name = name.replaceAll("\\", "");
            name = name.replaceAll(".", "");
            name = name.replaceAll("&", "");
            name = name + "-report";
            File file = new File("/usr/local/tmp/" + name);     /* Safe */
            if(file != null){
                file.delete();
            }
        }
    }
}
