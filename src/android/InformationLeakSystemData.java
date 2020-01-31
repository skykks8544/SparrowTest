package android;

import java.io.IOException;

public class InformationLeakSystemData {

    public void unsafe(){
         try{
             throw new IOException();
         } catch (IOException e){
             System.err.println(e.getMessage());        /* Bug */
         }
    }

    public void safe(){
        try{
            throw new IOException();
        } catch (IOException e){
            System.err.println("IOException occurred"); /* Safe */
        }
    }
}
