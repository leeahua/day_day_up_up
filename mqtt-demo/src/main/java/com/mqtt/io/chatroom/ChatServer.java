package com.mqtt.io.chatroom;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
* 聊天室服务器 消息接收 消息转发
* @author liyaohua
* @date 19:53 2020/6/9
* **/
public class ChatServer {

    private ServerSocketChannel serverSocketChannel = null;
    private Selector selector = null;
    private int BIND_PORT = 9999;

    public ChatServer(){
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            selector = Selector.open();
            serverSocketChannel.bind(new InetSocketAddress(BIND_PORT));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receiveData(){
        SocketChannel socketChannel = null;
        while (true) {
            try {
                int events = selector.select();
                if (events > 0) {
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        if (key.isAcceptable()) {
                            socketChannel = serverSocketChannel.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ);
                            System.out.println("client [" + socketChannel.getRemoteAddress().toString().substring(1) + "] online!");
                        } else if (key.isReadable()) {
                            socketChannel = (SocketChannel) key.channel();
                            ByteBuffer byteBuffer = ByteBuffer.allocate(128);
                            socketChannel.read(byteBuffer);
                            System.out.println("msg from client:" + new String(byteBuffer.array()));
                            byteBuffer.flip();
                            writeData(byteBuffer, socketChannel);
                            byteBuffer.clear();
                        }else if (key.isConnectable()){
                            socketChannel = (SocketChannel) key.channel();
                        }
                        iterator.remove();
                    }
                }else{
                    System.out.println("empty data");
                }
            } catch (IOException e) {
                if (socketChannel != null) {
                    try {
                        System.out.println("client [" + socketChannel.getRemoteAddress().toString().substring(1) + "] offline!");
                        socketChannel.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                e.printStackTrace();
            }
        }
    }

    public void writeData(ByteBuffer byteBuffer, SocketChannel socketChannel){
        try{
            Set<SelectionKey> keys = selector.keys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while(iterator.hasNext()){
                SelectionKey key = iterator.next();
                if(key.channel() instanceof SocketChannel && key.channel() != socketChannel){
                    SocketChannel channel = (SocketChannel)key.channel();
                    channel.write(byteBuffer);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
        new Thread(new Runnable() {
            @Override
            public void run() {
                chatServer.receiveData();
            }
        }).start();
    }




}
