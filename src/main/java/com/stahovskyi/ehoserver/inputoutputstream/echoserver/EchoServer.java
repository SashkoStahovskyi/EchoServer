package com.stahovskyi.ehoserver.inputoutputstream.echoserver;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class EchoServer {

    private static final int PORT = 3000;
    private static final int DEFAULT_BUFFER_CAPACITY = 100;

    public static void main(String[] args) throws IOException {
        EchoServer server = new EchoServer();
        server.run();
    }

    void run() throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            while (true) {

                try (Socket socket = serverSocket.accept();
                     BufferedInputStream inputSocketStream = new BufferedInputStream(socket.getInputStream());
                     BufferedOutputStream outputSocketStream = new BufferedOutputStream(socket.getOutputStream())) {

                    byte[] buffer = new byte[DEFAULT_BUFFER_CAPACITY];
                    int count = inputSocketStream.read(buffer);
                    String inputMassage = new String(buffer, 0, count);

                    System.out.println(" CLIENT >>  " + inputMassage);
                    String ehoAnswer = "ECHO " + inputMassage;
                    outputSocketStream.write(ehoAnswer.getBytes(StandardCharsets.UTF_8));

                }
            }
        }
    }
}
