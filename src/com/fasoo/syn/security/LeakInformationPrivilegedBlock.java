package com.fasoo.syn.security;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

public class LeakInformationPrivilegedBlock {

    private static Test test;
    private static GrandTest grandTest;

    public class GrandTest extends Test {
        // More implementation here ...
    }

    public class Test implements PrivilegedAction<Void> {
        public Void run() {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }
    }

    public static PrivilegedAction<Void> getPrivilegedAction() {
        PrivilegedAction<Void> ret = null;
        // Do something ...
        return ret;
    }

    public static PrivilegedExceptionAction<FileInputStream> getPrivilegedExceptionAction() {
        PrivilegedExceptionAction<FileInputStream> ret = null;
        // Do something ...
        return ret;
    }

    public static Test getTest() {
        // Do something ...
        return test;
    }

    public static GrandTest getGrandTest() {
        // Do something ...
        return grandTest;
    }

    public static void changePassword() {
        FileInputStream fin = null;
        try {
            fin = openPasswordFile();
        } catch (FileNotFoundException e) {
            // Do something ...
        } catch (PrivilegedActionException e) {
            // Do something ...
        }
        if (fin == null) {
            // no password file; handle error
        }

        // test old password with password in file contents; change password
    }

    public static FileInputStream publicPasswordFile() {
        AccessController.doPrivileged(new PrivilegedAction<Void>() { /* BUG */
            public Void run() {
                try {
                    FileInputStream[] fin = new FileInputStream[1];
                    String password_file = "something";
                    // Sensitive action; can't be done outside
                    // doPrivileged() block
                    fin[0] = new FileInputStream(password_file);
                } catch (FileNotFoundException x) {
                    // report to handler
                }
                return null;
            }
        });
        return null;
    }

    private static FileInputStream openPasswordFile() throws FileNotFoundException, PrivilegedActionException {

        PrivilegedAction<Void> action = null;
        PrivilegedExceptionAction<FileInputStream> exceptionAction = null;
        PrivilegedAction<FileInputStream> fileAction = null;
        Test testAction = null;

        // Do something ...

        final String password_file = "password";
        final FileInputStream fin[] = { null };

        try {
            fin[0] = AccessController.doPrivileged( /* BUG */
                    new PrivilegedExceptionAction<FileInputStream>() {
                        public FileInputStream run() throws FileNotFoundException {
                            // Sensitive action; can't be done outside privileged block
                            FileInputStream in = new FileInputStream(password_file);
                            return in;
                        }
                    });
        } catch (PrivilegedActionException x) {
            Exception cause = x.getException();
            if (cause instanceof FileNotFoundException) {
                throw (FileNotFoundException) cause;
            } else {
                throw new Error("Unexpected exception type", cause);
            }
        }

        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                try {
                    // Sensitive action; can't be done outside
                    // doPrivileged() block
                    fin[0] = new FileInputStream(password_file);
                } catch (FileNotFoundException x) {
                    // report to handler
                }
                return null;
            }
        });

        AccessController.doPrivileged(getPrivilegedAction());
        AccessController.doPrivileged(getPrivilegedExceptionAction()); /* BUG */
        AccessController.doPrivileged(getTest());
        AccessController.doPrivileged(getGrandTest());
        AccessController.doPrivileged(action);
        AccessController.doPrivileged(exceptionAction); /* BUG */
        AccessController.doPrivileged(fileAction); /* BUG */
        AccessController.doPrivileged(testAction);

        return fin[0];
    }
}
