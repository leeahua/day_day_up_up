package com.mqtt.io.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 *
 */

public class NioServer {
    private static Logger log = LoggerFactory.getLogger(NioServer.class);
    public static void main(String[] args) {
        try {
            //open
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(9090));
            while(true){
                System.out.println("begin listener..");
                SocketChannel socketChannel = serverSocketChannel.accept();
                if(socketChannel != null){
                    //处理客户连接
                    System.out.println("client "+socketChannel.getRemoteAddress()+ "online");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
