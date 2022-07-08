package com.stahovskyi.ehoserver.inputoutputstream.client;

import com.stahovskyi.ehoserver.inputoutputstream.socketmanager.SocketManager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SecondClient {

    private static final int PORT = 3000;

    public static void main(String[] args) {

        try (Socket clientSocket = new Socket("localhost", PORT);
             BufferedInputStream inputSocketStream = new BufferedInputStream(clientSocket.getInputStream());
             BufferedOutputStream outputSocketStream = new BufferedOutputStream(clientSocket.getOutputStream())) {

            if (clientSocket.isConnected()) {
                System.out.println("  <-- CONNECTED TO THE SERVER SUCCESSFULLY !! --> ");
            }

            while (true) {

                outputSocketStream.write(SocketManager.readMassageFromConsole());
                outputSocketStream.flush();

                String inputMassage = SocketManager.readInputMassage(inputSocketStream);
                System.out.println("SERVER >>" + inputMassage);
            }

        } catch (IOException exception) {
            exception.fillInStackTrace();
        }
    }
}

