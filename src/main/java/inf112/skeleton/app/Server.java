package inf112.skeleton.app;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static ServerSocket serverSocket;
    static Socket socket;
    static DataOutputStream out;

    public Server() throws IOException {
        System.out.println("Starting server...");
        serverSocket = new ServerSocket(7777);
        System.out.println("Server started...");
        socket = serverSocket.accept();
        System.out.println("Connection from: " + socket.getInetAddress());
        out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF("This is a of Java sockets");
        System.out.println("Data has been sent.");
    }

}
