package com.mqtt.io.chatroom;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/***
* room client
* @author liyaohua
* @date 20:45 2020/6/9
* **/
public class ChatClient {

    private String ADDRESS = "127.0.0.1";
    private int PORT = 9999;
    private SocketChannel socketChannel = null;
    private Selector selector = null;

    public ChatClient(){
        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            InetSocketAddress inetSocketAddress = new InetSocketAddress(ADDRESS,PORT);
            if(!socketChannel.connect(inetSocketAddress)){
                while (!socketChannel.finishConnect()){
                    System.out.println("client connecting");
                    Thread.sleep(2000);
                }
            }
            selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_READ);
            System.out.println("client started!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
    *  读取消息
    *
    * @return void
    * @author liyaohua
    * @date 20:53 2020/6/9
    * **/
    public void readData(){
        System.out.println("client started!");
        SocketChannel socketChannel = null;
        try {
            while (true){
                int events = selector.select(1000);
                if(events >0){
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()){
                        SelectionKey key = iterator.next();
                        if(key.isReadable()){
                            socketChannel = (SocketChannel)key.channel();
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                            socketChannel.read(byteBuffer);
                            System.out.println("client receive:" + new String(byteBuffer.array()));
                         }
                        iterator.remove();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("server lost");

            if(socketChannel != null){
                try {
                    socketChannel.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    /**
    * 发送数据
    * @param byteBuffer
    * @return void
    * @author liyaohua
    * @date 20:54 2020/6/9
    * **/
    public void writeData(ByteBuffer byteBuffer){
        try{
            socketChannel.write(byteBuffer);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ChatClient client = new ChatClient();
        new Thread(new Runnable() {
            @Override
            public void run() {
                client.readData();
            }
        }).start();
        Scanner s = new Scanner(System.in);
        while(s.hasNextLine()){
            String data = s.nextLine();
            System.out.println("data:"+data);
            client.writeData(ByteBuffer.wrap(data.getBytes()));
        }
    }
}
