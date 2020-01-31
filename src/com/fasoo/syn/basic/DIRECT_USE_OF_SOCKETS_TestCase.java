package com.fasoo.syn.basic;

import java.net.Socket;
import java.io.IOException;
import java.net.UnknownHostException;

public class DIRECT_USE_OF_SOCKETS_TestCase {

    public void doGet() {
        try {
            Socket sock = new Socket("localhost", 21); /* BUG */
            System.out.println(sock);
        } catch (UnknownHostException e) {
            System.out.println(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
