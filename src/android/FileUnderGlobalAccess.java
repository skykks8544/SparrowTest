package android;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileUnderGlobalAccess {

    private String MODE_WORLD_READABLE = "mode";

    /* For test case */
    private FileOutputStream openFileOutput(String test, String mode){
        return null;
    }

    public void unsafe(){
        FileOutputStream out = openFileOutput("test", MODE_WORLD_READABLE);
        OutputStreamWriter writer = new OutputStreamWriter(out);
        // Do something ...
        try {
            writer.write("Hello World");
        } catch (IOException e) {
            // Handling exception
        }
    }
}
