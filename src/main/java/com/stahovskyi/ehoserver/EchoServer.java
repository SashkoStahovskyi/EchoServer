package com.stahovskyi.ehoserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private static final int PORT = 3000;

    public static void main(String[] args) throws IOException {
        EchoServer server = new EchoServer();
        server.run();
    }

    void run() throws IOException {
        String request;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("  << -- SERVER STARTED -- >> ");

            while (true) {

                try (Socket socket = serverSocket.accept();

                     BufferedReader socketInputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     BufferedWriter socketOutputWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

                    while ((request = socketInputReader.readLine()) != null) {
                        System.out.println(" CLIENT MASSAGE -> " + request);

                        socketOutputWriter.write(" ECHO " + request + "\n");
                        socketOutputWriter.flush();
                    }
                }
            }
        }
    }
}

