package com.stahovskyi.ehoserver.inputoutputstream.socketmanager;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class SocketManager {

    private static final int DEFAULT_BUFFER_CAPACITY = 100;

    public static String readInputMassage(BufferedInputStream in) throws IOException {
        byte[] buffer = new byte[DEFAULT_BUFFER_CAPACITY];
        int count;
        count = in.read(buffer);

        if (count == -1) {
            return "";
        }
        return new String(buffer, 0, count) + "\n";
    }

    public static byte[] readMassageFromConsole() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print(" << WRITE YOUR MASSAGE >> : ");
        String massage = scanner.nextLine();
        return massage.getBytes(StandardCharsets.UTF_8);
    }
}
