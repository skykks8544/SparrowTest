package android;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class CleartextTransmissionSensitive {
    public void unsafe(){
        int port = 443;
        String hostname = "www.hostname.com";
        try {
            Socket socket = new Socket(hostname, port);     /* Bug */
            InputStream in = socket.getInputStream();
            // Do something ...
        } catch (IOException e) {
            // Handling exception
        }
    }

    public void safe(){
        int port = 443;
        String hostname = "www.hostname.com";
        try {
            SocketFactory socketFactory = SSLSocketFactory.getDefault();
            Socket socket = socketFactory.createSocket(hostname, port);     /* Safe */
            InputStream in = socket.getInputStream();
            // Do something ...
        } catch (IOException e) {
            // Handling exception
        }
    }
}
