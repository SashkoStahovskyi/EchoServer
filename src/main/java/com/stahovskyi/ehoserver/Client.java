package com.stahovskyi.ehoserver;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class Client {

    private static final int PORT = 3000;

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }

    void run() {

        try (Socket clientSocket = new Socket("localhost", PORT);

             BufferedReader consoleInputReader = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader socketInputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             BufferedWriter socketOutputWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {

            validateConnection(clientSocket);

            while (true) {

                String consoleMassage = consoleInputReader.readLine();
                if (consoleMassage.equals("stop")) {
                    break;
                }

                socketOutputWriter.write(consoleMassage + "\n");
                socketOutputWriter.flush();

                String inputMassage = socketInputReader.readLine();
                System.out.println(" SERVER MASSAGE -> " + inputMassage);
            }

            System.out.println(" <<-- THE CONNECTION IS STOPPED !! -->> ");

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void validateConnection(Socket clientSocket) throws SocketException {
        if (clientSocket.isConnected()) {
            System.out.println(" <-- CONNECTED TO THE SERVER SUCCESSFULLY !! --> ");
            System.out.println(" << WRITE YOUR MASSAGE >> ");
        } else throw new SocketException(" << !! CONNECTION IS FAILED !! >> ");
    }
}


