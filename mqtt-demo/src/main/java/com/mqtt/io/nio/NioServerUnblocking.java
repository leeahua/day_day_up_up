package com.mqtt.io.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 *
 */

public class NioServerUnblocking {

    private static Logger log = LoggerFactory.getLogger(NioServerUnblocking.class);

    public static void main(String[] args)  {
        ServerSocketChannel serverSocketChannel = null;
        try {
            //open
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(9090));
            serverSocketChannel.configureBlocking(false);
            Selector selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true){
                //如果没有事件触发，则返回
                int events = selector.select(1000);
                if( events == 0){
                    //TODO no client connect
                    System.out.println("server listenning ...");
                    continue;
                }
                //获取有事件发生的的selectKey
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()){
                    SelectionKey selectionKey = iterator.next();
                    if(selectionKey.isAcceptable()){
                        //TODO 处理新客户端连接事件
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    }else if(selectionKey.isConnectable()){
                        //TODO 处理connect事件

                    }else if(selectionKey.isReadable()){
                        //TODO 处理读事件
                        //获取可读事件的channel
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
                        socketChannel.read(byteBuffer);
                        System.out.println("message from client:"+ new String(byteBuffer.array()));
                    }else if(selectionKey.isWritable()){
                        //TODO 处理写事件
                    }
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                serverSocketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
