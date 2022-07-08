package com.stahovskyi.ehoserver.readerwriter.client1;

import java.io.*;
import java.net.Socket;

public class FirstClient1 {

    private static final int PORT = 3000;
    private static final int NUMBER_OF_SESSIONS = 2;

    public static void main(String[] args) {
        int count = 0;

        try (Socket clientSocket = new Socket("localhost", PORT);

             BufferedReader consoleInputReader = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader socketInputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             BufferedWriter socketOutputWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {

            if (clientSocket.isConnected()) {

                System.out.println(" <-- CONNECTED TO THE SERVER SUCCESSFULLY !! --> ");
                System.out.println(" << WRITE YOUR MASSAGE >> ");
            }

            while (count < NUMBER_OF_SESSIONS) {

                String consoleMassage = consoleInputReader.readLine();
                socketOutputWriter.write(consoleMassage + "\n");
                socketOutputWriter.flush();

                String inputMassage = socketInputReader.readLine();
                System.out.println(" SERVER MASSAGE -> " + inputMassage);
                count++;
            }

            System.out.println(" <<-- THE CONNECTION IS STOPPED !! -->> ");

        } catch (IOException exception) {
            exception.fillInStackTrace();
        }
    }
}



