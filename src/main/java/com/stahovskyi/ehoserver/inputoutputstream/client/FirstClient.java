package com.stahovskyi.ehoserver.inputoutputstream.client;

import com.stahovskyi.ehoserver.inputoutputstream.socketmanager.SocketManager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

public class FirstClient {

    private static final int PORT = 3000;
    private static final int NUMBER_OF_SESSIONS = 2;

    public static void main(String[] args) {
        int count = 0;

        try (Socket clientSocket = new Socket("localhost", PORT);
             BufferedInputStream inputSocketStream = new BufferedInputStream(clientSocket.getInputStream());
             BufferedOutputStream outputSocketStream = new BufferedOutputStream(clientSocket.getOutputStream())) {

            if (clientSocket.isConnected()) {
                System.out.println("  <-- CONNECTED TO THE SERVER SUCCESSFULLY !! --> ");
            }

            while (count < NUMBER_OF_SESSIONS) {

                outputSocketStream.write(SocketManager.readMassageFromConsole());
                outputSocketStream.flush();

                String inputMassage = SocketManager.readInputMassage(inputSocketStream);
                System.out.println(" SERVER >> " + inputMassage);
                count++;
            }
            System.out.println(" <<-- THE CONNECTION IS STOPPED !! -->> ");

        } catch (IOException exception) {
            exception.fillInStackTrace();
        }
    }
}

