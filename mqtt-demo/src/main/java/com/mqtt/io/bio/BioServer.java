package com.mqtt.io.bio;

import org.springframework.stereotype.Service;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SocketChannel;


public class BioServer {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(9090);
            System.out.println("server start ...");
            Socket client = serverSocket.accept();
            boolean flag = true;
            while (flag){
                while (client != null){
                    flag = process(client);
                    client = null;
                }
                if(!flag){
                    System.out.println("server stop now");
                    break;
                }
                System.out.println("server sleep now");
                Thread.sleep(1000);
                System.out.println("server wake up now");
                client = null;
                client = serverSocket.accept();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static boolean process(Socket client) throws IOException {
        boolean goon  = true;
        System.out.println("client:"+client.getInetAddress()+"online!");
        BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String msg = reader.readLine();
        System.out.println("client say:"+ msg);
        if(msg.equals("exit")){
            goon = false;
        }
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        writer.write(msg+"\n");
        writer.flush();
        System.out.println("client:"+client.getInetAddress()+"offline!");
        return goon;
    }
}
