package com.mqtt.io.bytebuffer;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MapperedBufferTest {

    public static void main(String[] args) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("1.txt","rw");
            FileChannel fileChannel = randomAccessFile.getChannel();
            /*
            * 参数1 ： FileChannel.MapMode.READ_WRITE 读写模式
            * 参数2 ： 可以读写的位置
            * 参数3 : 映射到内存的大小
            * */
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
            mappedByteBuffer.put(0, (byte)'L');
            mappedByteBuffer.put(1, (byte)'Y');
            mappedByteBuffer.put(2, (byte)'H');
            randomAccessFile.close();
            System.out.println("success");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
