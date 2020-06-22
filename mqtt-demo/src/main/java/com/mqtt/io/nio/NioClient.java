package com.mqtt.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioClient {

    public static void main(String[] args) {
        SocketChannel socketChannel = null;
        try {
            socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("127.0.0.1",9090));

            String msg = "hello server!";
            ByteBuffer byteBuffer = ByteBuffer.allocate(64);
            byteBuffer.clear();
            byteBuffer.put(msg.getBytes());
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()){
                socketChannel.write(byteBuffer);
            }
            socketChannel.finishConnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
