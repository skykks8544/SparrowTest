package com.fasoo.syn;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class WeakEncryptionInsufficientKeySize{
    int keySize=512;

    public void unsafe() throws Exception{
        int ks=511;

        KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(512, null); /* BUG */
        keyPairGenerator.initialize(ks+keySize); /* BUG */
        keyPairGenerator.initialize(keySize); /* BUG */
        KeyPair keyPair=keyPairGenerator.generateKeyPair();
    }

    public void safe() throws Exception{
        KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048+keySize, null); /* SAFE */
        KeyPair keyPair=keyPairGenerator.generateKeyPair();
    }
}
