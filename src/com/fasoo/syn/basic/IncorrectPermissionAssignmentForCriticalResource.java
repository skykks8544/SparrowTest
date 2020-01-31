package com.fasoo.syn.basic;

import java.io.File;
import java.lang.Exception;
import java.lang.String;

public class IncorrectPermissionAssignmentForCriticalResource {

    public void run() throws Exception {

        Runtime runtime = Runtime.getRuntime();
        String command1 = "umask 0";
        String[] env = new String[] {};

        String criticalFileName = "C:\\Critical.dat";
        File criticalFile = new File(criticalFileName);

        // Use of "umask 0" directly
        Runtime.getRuntime().exec("umask 0"); /* BUG */
        Runtime.getRuntime().exec("umask 0", new String[] {}); /* BUG */
        Runtime.getRuntime().exec("umask 0", new String[] {}, new File("C:\\Critical.dat")); /* BUG */
        Runtime.getRuntime().exec("umask 0", new String[] {}, new File(criticalFileName)); /* BUG */
        Runtime.getRuntime().exec("umask 0", env, criticalFile); /* BUG */

        // Use of new String[]{"umask 0"}
        Runtime.getRuntime().exec(new String[] { "umask 0" }); /* BUG */
        Runtime.getRuntime().exec(new String[] { "umask 0" }, new String[] {}); /* BUG */
        Runtime.getRuntime().exec(new String[] { "umask 0" }, new String[] {}, new File("C:\\Critical.dat")); /* BUG */
        Runtime.getRuntime().exec(new String[] { "umask 0" }, new String[] {}, new File(criticalFileName)); /* BUG */
        Runtime.getRuntime().exec(new String[] { "umask 0" }, env, criticalFile); /* BUG */

        // Use of new String[]{"umask", "0"}
        Runtime.getRuntime().exec(new String[] { "umask", "0" }); /* BUG */
        Runtime.getRuntime().exec(new String[] { "umask", "0" }, new String[] {}); /* BUG */
        Runtime.getRuntime().exec(new String[] { "umask", "0" }, new String[] {}, new File("C:\\Critical.dat")); /* BUG */
        Runtime.getRuntime().exec(new String[] { "umask", "0" }, new String[] {}, new File(criticalFileName)); /* BUG */
        Runtime.getRuntime().exec(new String[] { "umask", "0" }, env, criticalFile); /* BUG */

        // Use of "umask 0" directly for runtime variable
        runtime.exec("umask 0"); /* BUG */
        runtime.exec("umask 0", new String[] {}); /* BUG */
        runtime.exec("umask 0", new String[] {}, new File("C:\\Critical.dat")); /* BUG */
        runtime.exec("umask 0", new String[] {}, new File(criticalFileName)); /* BUG */
        runtime.exec("umask 0", env, criticalFile); /* BUG */

        // Use of new String[]{"umask 0"} for runtime variable
        runtime.exec(new String[] { "umask 0" }); /* BUG */
        runtime.exec(new String[] { "umask 0" }, new String[] {}); /* BUG */
        runtime.exec(new String[] { "umask 0" }, new String[] {}, new File("C:\\Critical.dat")); /* BUG */
        runtime.exec(new String[] { "umask 0" }, new String[] {}, new File(criticalFileName)); /* BUG */
        runtime.exec(new String[] { "umask 0" }, env, criticalFile); /* BUG */

        // Use of the command in a variable
        Runtime.getRuntime().exec(command1); /* BUG */
        Runtime.getRuntime().exec(command1, new String[] {}); /* BUG */
        Runtime.getRuntime().exec(command1, new String[] {}, new File("C:\\Critical.dat")); /* BUG */
        Runtime.getRuntime().exec(command1, new String[] {}, new File(criticalFileName)); /* BUG */
        Runtime.getRuntime().exec(command1, env, criticalFile); /* BUG */

        // Use of the command in a variable - the second case
        String[] command2 = new String[] { "umask 0" };
        Runtime.getRuntime().exec(command2); /* BUG */
        Runtime.getRuntime().exec(command2, new String[] {}); /* BUG */
        Runtime.getRuntime().exec(command2, new String[] {}, new File("C:\\Critical.dat")); /* BUG */
        Runtime.getRuntime().exec(command2, new String[] {}, new File(criticalFileName)); /* BUG */
        Runtime.getRuntime().exec(command2, env, criticalFile); /* BUG */

        // Use of the command in a variable - the third case
        String[] command3 = new String[] { "umask", "0" };
        Runtime.getRuntime().exec(command3); /* BUG */
        Runtime.getRuntime().exec(command3, new String[] {}); /* BUG */
        Runtime.getRuntime().exec(command3, new String[] {}, new File("C:\\Critical.dat")); /* BUG */
        Runtime.getRuntime().exec(command3, new String[] {}, new File(criticalFileName)); /* BUG */
        Runtime.getRuntime().exec(command3, env, criticalFile); /* BUG */

        // Safe cases and use of the commands in a variable
        command1 = "umask 77";
        Runtime.getRuntime().exec(command1);
        Runtime.getRuntime().exec(command1, new String[] {});
        Runtime.getRuntime().exec(command1, new String[] {}, new File("C:\\Critical.dat"));
        Runtime.getRuntime().exec(command1, new String[] {}, new File(criticalFileName));
        Runtime.getRuntime().exec(command1, env, criticalFile);

        command2 = new String[] { "umask 77" };

        Runtime.getRuntime().exec(command2);
        Runtime.getRuntime().exec(command2, new String[] {});
        Runtime.getRuntime().exec(command2, new String[] {}, new File("C:\\Critical.dat"));
        Runtime.getRuntime().exec(command2, new String[] {}, new File(criticalFileName));
        Runtime.getRuntime().exec(command2, env, criticalFile);

        command3 = new String[] { "umask", "77" };

        Runtime.getRuntime().exec(command3);
        Runtime.getRuntime().exec(command3, new String[] {});
        Runtime.getRuntime().exec(command3, new String[] {}, new File("C:\\Critical.dat"));
        Runtime.getRuntime().exec(command3, new String[] {}, new File(criticalFileName));
        Runtime.getRuntime().exec(command3, env, criticalFile);
    }

    public void safeRun() throws Exception {
        String criticalFileName = "C:\\Critical.dat";
        Runtime.getRuntime().exec("umask 0"); /* SAFE */
        Runtime.getRuntime().exec("umask 0", new String[] {}); /* SAFE */
        Runtime.getRuntime().exec("umask 0", new String[] {}, new File("C:\\Critical.dat")); /* BUG */
        Runtime.getRuntime().exec("umask 0", new String[] {}, new File(criticalFileName)); /* BUG */
    }

    public void bad() throws Throwable {
        File file = new File("c:\\report.txt");
        /* FLAW */
        file.setExecutable(true);
        file.setReadable(true);
        file.setWritable(true);
    }
}
