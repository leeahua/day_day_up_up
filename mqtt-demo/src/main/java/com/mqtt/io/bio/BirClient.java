package com.mqtt.io.bio;

import org.springframework.boot.CommandLineRunner;

import java.io.*;
import java.net.Socket;

public class BirClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 9090);
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            //send msg to server
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
            writer.write("exit"+"\n");
            writer.flush();
            //read server msg
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String msg = reader.readLine();
            System.out.println("receive server msg:"+msg);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }
    }
}
