package com.fasoo.syn.basic;

import java.net.DatagramSocket;
import java.net.ServerSocket;

public class MultipleBindsToTheSamePort {
    final int INPORT = 1711;

    public void foo() throws Exception {
        DatagramSocket socket1 = new DatagramSocket(INPORT); /* Bug */
        ServerSocket socket2 = new ServerSocket(INPORT); /* Bug */
        DatagramSocket socket3; /* Bug */
        ServerSocket socket4 = null;

        socket2.setReuseAddress(true);
        socket2.setReuseAddress(false);

        socket4.setReuseAddress(false);
        socket4.setReuseAddress(true);
    }
}
