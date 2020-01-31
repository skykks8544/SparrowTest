package com.fasoo.syn;

import java.io.File;

public class IncorrectPermissionAssignmentForCriticalResource{
    public void run() throws Exception{
        Runtime runtime=Runtime.getRuntime();
        String command1="umask 0";

        String[] env=new String[]{};
        File file=new File("d:\\Develop\\project\\intellij\\fasoo\\sparrow\\jf_syn\\pom.xml.bak");

        Runtime.getRuntime().exec("umask 0"); /* BUG */
        Runtime.getRuntime().exec("umask 0", new String[]{}); /* BUG */
        Runtime.getRuntime().exec("umask 0", new String[]{}, new File("1")); /* BUG */
        Runtime.getRuntime().exec("umask 0", env, file); /* BUG */

        Runtime.getRuntime().exec(new String[]{"umask 0"}); /* BUG */
        Runtime.getRuntime().exec(new String[]{"umask 0"}, new String[]{}); /* BUG */
        Runtime.getRuntime().exec(new String[]{"umask 0"}, new String[]{}, new File("1")); /* BUG */
        Runtime.getRuntime().exec(new String[]{"umask 0"}, env, file); /* BUG */

        Runtime.getRuntime().exec(new String[]{"umask", "0"}); /* BUG */
        Runtime.getRuntime().exec(new String[]{"umask", "0"}, new String[]{}); /* BUG */
        Runtime.getRuntime().exec(new String[]{"umask", "0"}, new String[]{}, new File("1")); /* BUG */
        Runtime.getRuntime().exec(new String[]{"umask", "0"}, env, file); /* BUG */

        runtime.exec("umask 0"); /* BUG */
        runtime.exec("umask 0", new String[]{}); /* BUG */
        runtime.exec("umask 0", new String[]{}, new File("1")); /* BUG */
        runtime.exec("umask 0", env, file); /* BUG */

        runtime.exec(new String[]{"umask 0"}); /* BUG */
        runtime.exec(new String[]{"umask 0"}, new String[]{}); /* BUG */
        runtime.exec(new String[]{"umask 0"}, new String[]{}, new File("1")); /* BUG */
        runtime.exec(new String[]{"umask 0"}, env, file); /* BUG */

        runtime.exec(new String[]{"umask", "0"}); /* BUG */
        runtime.exec(new String[]{"umask", "0"}, new String[]{}); /* BUG */
        runtime.exec(new String[]{"umask", "0"}, new String[]{}, new File("1")); /* BUG */
        runtime.exec(new String[]{"umask", "0"}, env, file); /* BUG */

        Runtime.getRuntime().exec(command1); /* BUG */
        Runtime.getRuntime().exec(command1, new String[]{}); /* BUG */
        Runtime.getRuntime().exec(command1, new String[]{}, new File("1")); /* BUG */
        Runtime.getRuntime().exec(command1, env, file); /* BUG */

        String[] command2=new String[]{"umask 1"};
        Runtime.getRuntime().exec(command2);
        Runtime.getRuntime().exec(command2, new String[]{});
        Runtime.getRuntime().exec(command2, new String[]{}, new File("1"));
        Runtime.getRuntime().exec(command2, env, file);

        String[] coomand3=new String[]{"umask", "0"};
        Runtime.getRuntime().exec(coomand3); /* BUG */
        Runtime.getRuntime().exec(coomand3, new String[]{}); /* BUG */
        Runtime.getRuntime().exec(coomand3, new String[]{}, new File("1")); /* BUG */
        Runtime.getRuntime().exec(coomand3, env, file); /* BUG */

        command1="umask 1";
        runtime.exec(command1);
        runtime.exec(command1, new String[]{});
        runtime.exec(command1, new String[]{}, new File("1"));
        runtime.exec(command1, env, file);

        command2=new String[]{"umask 0"};
        runtime.exec(command2); /* BUG */
        runtime.exec(command2, new String[]{}); /* BUG */
        runtime.exec(command2, new String[]{}, new File("1")); /* BUG */
        runtime.exec(command2, env, file); /* BUG */

        coomand3=new String[]{"umask", "1"};
        runtime.exec(coomand3);
        runtime.exec(coomand3, new String[]{});
        runtime.exec(coomand3, new String[]{}, new File("1"));
        runtime.exec(coomand3, env, file);
    }
}
