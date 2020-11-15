package ru.mirea.task19_20;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Client {

    static String ip = "127.0.0.1";
    static int port = 1234;
    static int offset = 0;


    public static void sendMessage(DatagramSocket s) {
        while (true) {
            byte[] buff = new byte[2048];
            String msg;
            DatagramPacket packet = new DatagramPacket(buff, offset, buff.length);

            try {
                s.receive(packet);
                msg = new String(buff, offset, packet.getLength());
                System.out.println(msg);
            } catch (IOException e) {
                e.getStackTrace();
            }
        }
    }

    public static void getMessage(DatagramSocket socket) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            DatagramPacket toPacket;
            byte[] toSendBuffer = scanner.nextLine().getBytes();
            try {
                toPacket = new DatagramPacket(
                        toSendBuffer,
                        offset,
                        toSendBuffer.length,
                        InetAddress.getByName(ip),
                        port);
                socket.send(toPacket);
            } catch (UnknownHostException e) {
                e.getStackTrace();
            } catch (IOException e) {
                e.getStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Enter the port (can't be the same): ");
        int clientPort = new Scanner(System.in).nextInt();
        try {
            DatagramSocket socket = new DatagramSocket(clientPort);
            System.out.print("Enter your name: ");
            Thread thread = new Thread(() -> getMessage(socket));
            thread.start();
            thread = new Thread(() -> sendMessage(socket));
            thread.start();
        } catch (SocketException e) {
            e.getStackTrace();
        }
    }
}
