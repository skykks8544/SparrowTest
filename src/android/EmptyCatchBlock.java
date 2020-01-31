package android;

import java.io.FileReader;
import java.io.IOException;

public class EmptyCatchBlock {

    public void unsafe(String filePath) {
        try {
            // Read with the file name of the given option
            FileReader fr = new FileReader(filePath);
            fr.close();
        } catch(Exception e) {          /* BUG */
            // Empty catch block
        }
    }

    public void safe(String filePath) {
        FileReader fr = null;
        try {
            // Read with the file name of the given option
            fr = new FileReader(filePath);
            fr.close();
        } catch(Exception e) {          /* Safe */
            if(fr != null){
                try {
                    fr.close();
                } catch (IOException e1) {
                    fr = null;
                }
            }
        }
    }
}
