package pg.pt;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port;
    private ServerSocket server;

    private boolean running;
    private Socket client;

    private ObjectInputStream ois;
    public Server() throws IOException, ClassNotFoundException {
        port = 8080;
        running = true;

        server = new ServerSocket(port);
        System.out.println("Waiting for the client request");
        Socket socket = server.accept();
        System.out.println("Connected!");

        ois = new ObjectInputStream(socket.getInputStream());
        while(true){

            //convert ObjectInputStream object to String
            String message = (String) ois.readObject();
            System.out.println("Message Received: " + message);

            //terminate the server if client sends exit request
            if(message.equalsIgnoreCase("exit")) break;
        }
        ois.close();
        socket.close();
        System.out.println("Shutting down Socket server!!");
        //close the ServerSocket object
        server.close();

    }

}
