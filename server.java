// CN PROJECT
// GROUP NO. - 5
// SEC - U
// TEAM MEMBERS:
// 1. 2141011052, PEKAL KHUSI
// 2. 2141019432, NITYAM SWASTIK
// 3. 2141016211, SWAGATIKA PATRA
// 4. 2141019203, MOHIT KUMAR SARAF
// 5. 2141013087, EKANSH MENGHANI

import javax.sound.sampled.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
public class server {
    public static void main(String[] args) {
        try {
            int serverPort = 12345;
            System.out.println("Server is starting...");
            ServerSocket serverSocket = new ServerSocket(serverPort);
            System.out.println("Server is listening on port " + serverPort);
            System.out.println("Waiting for a client to connect...\n");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected: " + socket.getInetAddress().getHostAddress());
            AudioFormat audioFormat = new AudioFormat(16000, 16, 1, true, true);
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
            SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceDataLine.open(audioFormat);
            sourceDataLine.start();
            System.out.println("Speakers opened \nReceiving and playing audio...");
            byte[] buffer = new byte[1024];
            InputStream inputStream = socket.getInputStream();

            while (true) {
                int bytesRead = inputStream.read(buffer, 0, buffer.length);
                sourceDataLine.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}