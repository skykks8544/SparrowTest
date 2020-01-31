package com.fasoo.syn.basic;

import java.lang.String;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class WEAK_CRYPTOGRAPHIC_HASH_HARDCODED_SALT_TestCase {
    byte unsafeSaltByte=(byte)100;
    int n1=1, n2=2;

    public byte[] test(byte messageByte, byte[] messageBytes, String message) throws Exception{
        byte[] safeSaltByteArray=getBytes();
        byte[] unsafeSaltByteArray1=new byte[]{100, 101};
        byte[] unsafeSaltByteArray2=null;
        unsafeSaltByteArray2=new byte[]{100, 101};

        String safeSalt=getStr();
        String unsafeSalt1="1";
        String unsafeSalt2=null;
        String unsafeSalt3;

        unsafeSalt2="1";
        unsafeSalt3="1";

        MessageDigest messageDigest=MessageDigest.getInstance("SHA-256");

        //parameter safe
        messageDigest.update(messageByte);
        messageDigest.update(messageBytes);
        messageDigest.update(message.getBytes());

        messageDigest.update(safeSalt.getBytes());
        messageDigest.update(safeSalt.getBytes(), n1, n2);

        messageDigest.update((byte)12);										/* Bug */
        messageDigest.update(String.valueOf((byte)12).getBytes());
        messageDigest.update("SALT_100".getBytes("utf-8"));
        messageDigest.update("SALT_100".getBytes(), n1, n2);

        messageDigest.update(unsafeSaltByte);								/* Bug */
        unsafeSaltByte=getB();
        messageDigest.update(unsafeSaltByte);

        messageDigest.update(safeSaltByteArray);
        messageDigest.update(unsafeSaltByteArray1);					/* Bug */
        messageDigest.update(unsafeSaltByteArray1, n1, n2);		/* Bug */

        messageDigest.update(new String(unsafeSaltByteArray2).getBytes());
        messageDigest.update(new byte[]{12, 1});						/* Bug */
        messageDigest.update(unsafeSaltByteArray2);					/* Bug */
        messageDigest.update(new byte[]{12, getB()});				
        unsafeSaltByteArray2=new byte[]{100, 101, getB()};
        messageDigest.update(unsafeSaltByteArray2);
        unsafeSaltByteArray2[1]=getB();
        messageDigest.update(unsafeSaltByteArray2);
        messageDigest.update(unsafeSaltByteArray2, n1, n2);

        messageDigest.update(unsafeSalt1.getBytes());
        messageDigest.update(unsafeSalt2.getBytes());
        messageDigest.update(unsafeSalt3.getBytes());
        messageDigest.update(unsafeSalt3.getBytes(), n1, n2);

        return messageDigest.digest(messageBytes);
    }

    public byte[] randomTest(String message, byte[] messageBytes) throws Exception{
        try{
            MessageDigest messageDigest=MessageDigest.getInstance("SHA-256");
            SecureRandom secureRandom=new SecureRandom();
            String safeSalt=Integer.toString(secureRandom.nextInt());

            messageDigest.update(message.getBytes());
            messageDigest.update(messageBytes);
            messageDigest.update(messageBytes, n1, n2);

            messageDigest.update(safeSalt.getBytes());

            messageDigest.update(String.valueOf(new SecureRandom().nextInt()).getBytes());
            messageDigest.update(String.valueOf(new SecureRandom().nextLong()).getBytes());
            messageDigest.update(String.valueOf(secureRandom.nextGaussian()).getBytes());
            messageDigest.update(String.valueOf(secureRandom.nextInt()).getBytes());

            messageDigest.update((""+new SecureRandom().nextInt()).getBytes());
            messageDigest.update((""+secureRandom.nextInt()).getBytes());

            return messageDigest.digest(messageBytes);
        }catch(NoSuchAlgorithmException e){
            System.out.println(e);
        }
        return null;
    }

    public String getStr(){
        return "test";
    }

    public byte[] getBytes(){
        return new byte[]{};
    }

    public byte getB(){
        return 1;
    }
}
