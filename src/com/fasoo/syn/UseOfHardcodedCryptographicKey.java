package com.fasoo.syn;

import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import java.sql.DriverManager;

public class UseOfHardcodedCryptographicKey{
    SecretKeySpec secretKeySpec;
    byte[] bytes=new byte[]{1, 2, 3};
    String seed;

    public void unsafeEncrypt(DriverManager d, String external) throws Exception {
        seed="68af404b513073584c4b6f22b6c63e6b";
        byte[] seedBytes = seed.getBytes();

        secretKeySpec = new SecretKeySpec("68af404b513073584c4b6f22b6c63e6b".getBytes(), "AES"); /* BUG */
        secretKeySpec = new SecretKeySpec(new byte[]{1, 2}, "AES"); /* BUG */
        secretKeySpec = new SecretKeySpec(bytes, "AES"); /* BUG */
        secretKeySpec = new SecretKeySpec(seed.getBytes(), "AES"); /* BUG */
        secretKeySpec = new SecretKeySpec(seedBytes, "AES"); /* BUG */

        try {
            Cipher c = Cipher.getInstance("AES");
            byte[] bc = c.doFinal(new byte[]{1, 2});
            byte[] be = c.doFinal(external.getBytes());
            String sc = new String(bc);
            d.getConnection("url", "user", new String(bc)); /* BUG */
            d.getConnection("url", "user", sc); /* BUG */
            d.getConnection("url", "user", new String(be));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void ignoredEncrypt(DriverManager d, String external) throws Exception {
        seed="68af404b513073584c4b6f22b6c63e6b";
        byte[] seedBytes = seed.getBytes();

        secretKeySpec = new SecretKeySpec("68af404b513073584c4b6f22b6c63e6b".getBytes(), "DESede"); /* EXCLUDED */
        secretKeySpec = new SecretKeySpec(new byte[]{1, 2}, "DESede"); /* EXCLUDED */
        secretKeySpec = new SecretKeySpec(bytes, "DESede"); /* EXCLUDED */
        secretKeySpec = new SecretKeySpec(seed.getBytes(), "DESede"); /* EXCLUDED */
        secretKeySpec = new SecretKeySpec(seedBytes, "DESede"); /* EXCLUDED */

        try {
            Cipher c = Cipher.getInstance("DESede");
            byte[] bc = c.doFinal(new byte[]{1, 2});
            byte[] be = c.doFinal(external.getBytes());
            String sc = new String(bc);
            d.getConnection("url", "user", new String(bc)); /* EXCLUDED */
            d.getConnection("url", "user", sc); /* EXCLUDED */
            d.getConnection("url", "user", new String(be));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void safeEncrypt() throws Exception{
        bytes=getPasswordBytes();
        seed=getPassword();

        secretKeySpec=new SecretKeySpec(getPassword().getBytes(), "AES");
        secretKeySpec=new SecretKeySpec(getPasswordBytes(), "AES");
        secretKeySpec=new SecretKeySpec(bytes, "AES");
        secretKeySpec=new SecretKeySpec(seed.getBytes(), "AES");
    }

    public String getPassword(){
        return "68af404b513073584c4b6f22b6c63e6b";
    }

    public byte[] getPasswordBytes(){
        return "68af404b513073584c4b6f22b6c63e6b".getBytes();
    }
}
