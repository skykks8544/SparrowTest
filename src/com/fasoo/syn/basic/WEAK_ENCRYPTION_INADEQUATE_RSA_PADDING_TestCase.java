package com.fasoo.syn.basic;

import javax.crypto.Cipher;

public class WEAK_ENCRYPTION_INADEQUATE_RSA_PADDING_TestCase {
    private static final String RSA="RSA/NONE/NoPadding";
    private static Cipher cipher;

    static {
        try{
            cipher=javax.crypto.Cipher.getInstance("RSA/NONE/NoPadding", new sun.security.provider.Sun()); /* BUG */
        }catch(Exception e){}
    }

    public WEAK_ENCRYPTION_INADEQUATE_RSA_PADDING_TestCase() throws Exception{
        cipher=javax.crypto.Cipher.getInstance(RSA); /* BUG */
    }

    public Cipher getUnsafeCipher1() throws Exception{
        return javax.crypto.Cipher.getInstance(RSA, new sun.security.provider.Sun()); /* BUG */
    }

    public Cipher getUnsafeCipher2() throws Exception{
        Cipher cipher1=Cipher.getInstance("RSA/NONE/NoPadding"); /* BUG */
        return cipher1;
    }

    public Cipher getSafeCipher() throws Exception{
        return Cipher.getInstance("RSA/CBC/PKCS5Padding");
    }
}
