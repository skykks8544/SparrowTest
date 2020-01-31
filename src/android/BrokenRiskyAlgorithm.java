package android;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class BrokenRiskyAlgorithm {

    public void unsafe(byte[] msg, Key key){

        byte[] rslt = null;

        try {
            Cipher c= Cipher.getInstance("DES");    /* Bug */
            c.init(Cipher.ENCRYPT_MODE, key);
            rslt = c.update(msg);
            // Do something ...
        } catch (NoSuchAlgorithmException e) {
            // Handling exception
        } catch (NoSuchPaddingException e) {
            // Handling exception
        } catch (InvalidKeyException e) {
            // Handling exception
        }
    }

    public void safe(byte[] msg, Key key){

        byte[] rslt = null;

        try {
            Cipher c= Cipher.getInstance("AES/CBC/PKCS5Padding");    /* Safe */
            c.init(Cipher.ENCRYPT_MODE, key);
            rslt = c.update(msg);
            // Do something ...
        } catch (NoSuchAlgorithmException e) {
            // Handling exception
        } catch (NoSuchPaddingException e) {
            // Handling exception
        } catch (InvalidKeyException e) {
            // Handling exception
        }
    }
}
