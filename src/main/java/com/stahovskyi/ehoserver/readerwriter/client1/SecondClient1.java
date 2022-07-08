package com.stahovskyi.ehoserver.readerwriter.client1;

import java.io.*;
import java.net.Socket;

public class SecondClient1 {

    private static final int PORT = 3000;

    public static void main(String[] args) {

        try (Socket clientSocket = new Socket("localhost", PORT);

             BufferedReader consoleInputReader = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader socketInputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             BufferedWriter socketOutputWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {

            if (clientSocket.isConnected()) {

                System.out.println(" <-- CONNECTED TO THE SERVER SUCCESSFULLY !! --> ");
                System.out.println(" << WRITE YOUR MASSAGE >> ");
            }

            while (true) {

                String consoleMassage = consoleInputReader.readLine();
                socketOutputWriter.write(consoleMassage + "\n");
                socketOutputWriter.flush();

                String inputMassage = socketInputReader.readLine();
                System.out.println(" SERVER MASSAGE -> " + inputMassage);
            }

        } catch (IOException exception) {
            exception.fillInStackTrace();
        }
    }
}