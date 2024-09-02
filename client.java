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
import java.net.Socket;
public class client {
    public static void main(String[] args) {
        try {
        String serverIp = "192.168.113.194";
        int serverPort = 12345;

        AudioFormat audioFormat = new AudioFormat(16000, 16, 1, true, true);
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, audioFormat);
        TargetDataLine targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
        targetDataLine.open(audioFormat);
        targetDataLine.start();
        System.out.println("Microphone opened. \nConnecting to the server...");
        System.out.println(" ");
        Socket socket = new Socket(serverIp, serverPort);
        OutputStream outputStream = socket.getOutputStream();
        System.out.println("Connected to the server. \nStarting audio transmission...");
        byte[] buffer = new byte[1024];
        while (true) {
            int bytesRead = targetDataLine.read(buffer, 0, buffer.length);
            outputStream.write(buffer, 0, bytesRead);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}