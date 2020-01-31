package com.fasoo.sem;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class USING_HASH_WITHOUT_SALT_TestCase {
    public byte [] getHash(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        return digest.digest(password.getBytes("UTF-8")); /* BUG */
    }

    public byte [] getSafeHash(String password, byte [] salt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        digest.update(salt); // 솔트를 사용하여 안전하다.
        return digest.digest(password.getBytes("UTF-8")); /* SAFE */
    }
}