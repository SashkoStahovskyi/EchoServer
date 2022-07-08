package com.stahovskyi.ehoserver.readerwriter.echoserver1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer1 {

    private static final int PORT = 3000;

    public static void main(String[] args) throws IOException {
        EchoServer1 server = new EchoServer1();
        server.run();
    }

    void run() throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            while (true) {

                try (Socket socket = serverSocket.accept();

                     BufferedReader socketInputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     BufferedWriter socketOutputWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {


                    String inputMassage = socketInputReader.readLine();
                    System.out.println(" CLIENT MASSAGE -> " + inputMassage);

                    socketOutputWriter.write(" ECHO " + inputMassage + "\n");
                    socketOutputWriter.flush();
                }
            }
        }
    }
}

