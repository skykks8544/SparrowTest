package android;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class TocTou {
    public void onCreate() {
        FileReadThread fileReadThread = new FileReadThread();
        FileDeleteThread fileDeleteThread = new FileDeleteThread();
        fileReadThread.start();
        fileDeleteThread.start();
    }
}

class FileReadThread extends Thread {
    public void run() {
        try {
            File f = new File("Test_367.txt");
            if (f.exists()) { // file exist check
                BufferedReader br = new BufferedReader(new FileReader(f)); // file read
                br.readLine();                      /* BUG */
                br.close();
            }
        } catch (IOException e) { }
    } /* BUG */ // resource leak
}

class FileDeleteThread extends Thread {
    public void run() {
        File f = new File("Test_367.txt");
        if (f.exists()) { // file exist check
            f.delete();                             /* BUG */
        }
    }
}