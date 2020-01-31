package com.fasoo.syn;

import java.io.FileReader;

/**
 * 
 * @since 20120925
 */
public class EmptyCatchBlock {

    public void func1(String filePath) {
        try {
            // Read with the file name of the given option
            FileReader fr = new FileReader(filePath);
            fr.close();
        } catch(Exception e) { /* BUG */
        }
    }

    public void func2(String filePath) {
        try {
            // Read with the file name of the given option
            FileReader fr = new FileReader(filePath);
            fr.close();
        } catch(Exception e) { /* BUG */
            ;
        }
    }

    public void func3(String filePath) {
        try {
            // Read with the file name of the given option
            FileReader fr = new FileReader(filePath);
            fr.close();
        } catch(Exception e) { /* BUG */
            {
                ;
            }
        }
    }

    public void func4(String filePath) {
        try {
            // Read with the file name of the given option
            FileReader fr = new FileReader(filePath);
            fr.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void func5(String filePath) {
        try {
            // Read with the file name of the given option
            FileReader fr = new FileReader(filePath);
            fr.close();
        } catch(Exception e) {
            {
                ;
            }
            e.printStackTrace();
        }
    }
}
