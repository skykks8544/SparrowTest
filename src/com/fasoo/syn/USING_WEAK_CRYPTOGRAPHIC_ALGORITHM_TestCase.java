package com.fasoo.syn;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

/**
 * Writer: 
 * Date: 9/14/12
 */
public class USING_WEAK_CRYPTOGRAPHIC_ALGORITHM_TestCase {
    public void test(Properties prop) {
        try {
            Cipher c = Cipher.getInstance("DES"); /* BUG */
            Cipher c2 = Cipher.getInstance("AES/CBS/PKCS5Padding"); /* SAFE */
            byte [] password = Base64.decode(prop.getProperty("password")); /* BUG */
            MessageDigest md = MessageDigest.getInstance("MD5"); /* BUG */
        } catch (NoSuchAlgorithmException e) {

        } catch (NoSuchPaddingException e) {

        } catch (Base64DecodingException e) {

        }
    }

}
