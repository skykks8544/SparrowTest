package com.fasoo.syn.security;

import java.lang.System;

public class UsingDynamicClassLoading {
    public void dynamicLoading(){
        String className = System.getProperty("customClassName");

        try {
            Class clazz = Class.forName(className);     /* Bug */
            System.out.println(clazz);
        } catch (ClassNotFoundException e) {
            // Handling exception
        }
    }
}