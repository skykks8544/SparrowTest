package com.fasoo.sem;


import javax.servlet.http.HttpServletRequest;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * Date: 12. 12. 26.
 * Time: 오후 4:13
 */
public class WEAK_ENCRYPTION_INSUFFICIENT_KEY_SIZE_TestCase {
    /*
        Test case for taint analyzer
        Taint source :  SamateSrc
        Taint sink :    java.security.KeyPairGenerator.initialize(..)
     */
    public void test(HttpServletRequest request) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        int keySize = Integer.parseInt(request.getParameter("key_size"));
        keyPairGenerator.initialize(keySize, null); /* BUG */ // tainted

    }

    public void test2(HttpServletRequest request) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        int keySize = Integer.parseInt(request.getParameter("key_size"));
        keyPairGenerator.initialize(keySize); /* BUG */ // tainted

    }

    /*
        Test case for general case
        The key size for java.security.KeyPairGenerator.initialize method's first argument should be more than 1024.
        TODO: This test case should be detected by semantic engine.
     */
    public void test3() throws NoSuchAlgorithmException {
        int keySize = 512;
        int ks = 511;

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(ks + keySize); // alarm
        keyPairGenerator.initialize(keySize); // alarm
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        KeyPairGenerator keyPairGenerator2 = KeyPairGenerator.getInstance("AES");
        keyPairGenerator2.initialize(1023, null); // alarm
    }
}
