package com.mqtt.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioClinetUnblocking {

    public static void main(String[] args) {

        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 9090);
            if(!socketChannel.connect(inetSocketAddress)){
                while (!socketChannel.finishConnect()){
                    System.out.println("connecting ,do something else");
                }
            }
            String data = "hello, server!";
            ByteBuffer byteBuffer = ByteBuffer.wrap(data.getBytes());
            socketChannel.write(byteBuffer);
            System.in.read();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
