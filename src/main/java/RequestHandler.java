package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class RequestHandler implements Runnable {
    private final Socket socket;

    public RequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                OutputStream out = socket.getOutputStream()
        ) {
            String requestLine = in.readLine();
            if (requestLine == null) return;

            String htmlBody = getHtmlResponse();
            byte[] bodyBytes = htmlBody.getBytes(StandardCharsets.UTF_8);

            String httpResponse = "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: text/html; charset=UTF-8\r\n" +
                    "Content-Length: " + bodyBytes.length + "\r\n" +
                    "Connection: close\r\n" +
                    "\r\n";

            out.write(httpResponse.getBytes(StandardCharsets.UTF_8));
            out.write(bodyBytes);
            out.flush();

        } catch (IOException e) {
            System.err.println("İstek işleme hatası: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Soket kapatılamadı: " + e.getMessage());
            }
        }
    }

    private String getHtmlResponse() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"tr\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Öğrenci Biyografisi</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n" +
                "            background-color: #f4f7f6;\n" +
                "            color: #333333;\n" +
                "            margin: 40px;\n" +
                "            line-height: 1.6;\n" +
                "        }\n" +
                "        .card {\n" +
                "            background-color: #ffffff;\n" +
                "            padding: 30px;\n" +
                "            border-radius: 10px;\n" +
                "            box-shadow: 0 4px 8px rgba(0,0,0,0.1);\n" +
                "            max-width: 650px;\n" +
                "            margin: 0 auto;\n" +
                "        }\n" +
                "        h1 {\n" +
                "            color: #1a5276;\n" +
                "            border-bottom: 2px solid #1a5276;\n" +
                "            padding-bottom: 8px;\n" +
                "            margin-bottom: 5px;\n" +
                "        }\n" +
                "        h2 {\n" +
                "            color: #5d6d7e;\n" +
                "            font-weight: 400;\n" +
                "            margin-top: 0;\n" +
                "        }\n" +
                "        p {\n" +
                "            font-size: 16px;\n" +
                "            color: #424949;\n" +
                "        }\n" +
                "        .highlight {\n" +
                "            color: #e74c3c;\n" +
                "            font-weight: bold;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"card\">\n" +
                "        <h1>Zekeriya Yusuf Cezlan</h1>\n" +
                "        <h2>1250505031</h2>\n" +
                "        <hr>\n" +
                "        <h3>Kısa Biyografi</h3>\n" +
                "        <p>Merhaba, ben <span class=\"highlight\">Zekeriya Yusuf Cezlan</span>. Kırklareli Üniversitesi Yazılım Mühendisliği Bölümü 2. sınıf öğrencisiyim.</p>\n" +
                "        <p>Yazılım geliştirme, nesne yönelimli programlama (OOP), soket programlama ve veritabanı mimarileri üzerine çalışmalar yürütmekteyim. Öğrenci kulüplerinde teknik organizasyonlarda ve proje liderliği görevlerinde aktif olarak yer alıyorum.</p>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
    }
}