package de.code.server;

import de.code.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    private ServerSocket serverSocket;
    private boolean isRunning = false;

    public TCPServer() {
        try {
            serverSocket = new ServerSocket(4020);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startServer() {
        Thread serverThread = new Thread(this::start);
        serverThread.start();
    }

    private void start() {
        if (serverSocket == null) return;

        isRunning = true;

        while (isRunning) {
            try {
                Socket clientSocket = serverSocket.accept();


                handleClient(clientSocket);

            } catch (IOException e) {
                if (!isRunning) {


                } else {
                    e.printStackTrace();
                }
            }
        }
    }

    private void handleClient(Socket clientSocket) {
        try (
                InputStream inputStream = clientSocket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))
        ) {
            String message = reader.readLine();
            if (message != null) {

                Main.gui.addText(clientSocket.getInetAddress() + " : " + message);
            }

            Thread.sleep(2000);

            clientSocket.close();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        isRunning = false;

        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
