package com.fasoo.sem;

import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;

public class HARD_CODED_CRYPTOGRAPHIC_KEY_TestCase {
    public byte[] test(String url, String name) throws Exception {
        String data = "wlsalstlr";

        String sToEncrypt = "Super secret Squirrel";
        byte[] bToEncrypt = sToEncrypt.getBytes("UTF-8");
        SecretKeySpec sKeySpec = new SecretKeySpec(data.getBytes("UTF-8"), "AES"); /* BUG */
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.ENCRYPT_MODE, sKeySpec);
        byte[] bCipherText = aesCipher.doFinal(bToEncrypt);
        return bCipherText;
    }
}
