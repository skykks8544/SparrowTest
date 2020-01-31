package com.fasoo.sem.RL;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RLComplicatedTest {
    public int getSomeInt() { return 10;}
    public BufferedWriter getWriter() throws IOException {
        return new BufferedWriter(new FileWriter(new File("test.txt")));
    }
    public void test() {
        BufferedWriter out = null;
        try{
            int b = getSomeInt();
            if (b > 9) {
                out = getWriter();
                out.write("This is test.");
                out.newLine(); // Do not close resource
            } else {
                out = getWriter();
                out.write("This is test.");
                out.newLine();
                out.close();
            }
        } catch (IOException e) {
            // ...
        }
    } /* BUG */
}
