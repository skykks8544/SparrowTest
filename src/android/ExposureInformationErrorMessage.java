package android;

import java.io.IOException;

public class ExposureInformationErrorMessage {
    public void unsafe(){
        try {
            throw new IOException();
        } catch (IOException e) {
            e.printStackTrace();    /* Bug */
        }
    }

    public void safe(){
        try {
            throw new IOException();
        } catch (IOException e) {
            // Handling exception in other way, like a using logger.
        }
    }
}
