package de.code.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {

    private String serverAddress;
    private int serverPort;

    public TCPClient(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public void send(String message) {
        try (Socket socket = new Socket(serverAddress, serverPort);
             OutputStream outputStream = socket.getOutputStream()) {

            byte[] messageBytes = message.getBytes();
            outputStream.write(messageBytes);
            outputStream.flush();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
