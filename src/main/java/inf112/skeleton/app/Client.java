package inf112.skeleton.app;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    static Socket socket;
    static DataInputStream in;

    public Client() throws IOException {
        System.out.println("Connecting...");
        socket = new Socket("localhost", 7777);
        System.out.println("Connections successful");
        in = new DataInputStream(socket.getInputStream());
        System.out.println("Receiving information...");
        String test = in.readUTF();
        System.out.println("Message from server: + test");

    }
}
