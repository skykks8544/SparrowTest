package com.fasoo.syn.basic;

public class CRITICAL_PUBLIC_VARIABLE_WITHOUT_FINAL_MODIFIER_TestCase {
    public static float price=500;					/* Bug */
    public static final float safe1=0;
    public final static float safe2=0;

    public static int intField=1;					/* Bug */
    public static long longField=1;				/* Bug */
    public static String stringField="";
    public float floatField=500;

    public float getTotal(int count){
        return price*count;
    }
}
