package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.example.RequestHandler;

public class Main {
    private static final int PORT = 1989;

    public static void main(String[] args) {
        System.out.println("Web sunucusu " + PORT + " portunda başlatılıyor...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Sunucu hazır. Tarayıcıdan http://localhost:" + PORT + " adresine gidin.");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                RequestHandler requestHandler = new RequestHandler(clientSocket);
                new Thread(requestHandler).start();
            }
        } catch (IOException e) {
            System.err.println("Sunucu hatası: " + e.getMessage());
        }
    }
}