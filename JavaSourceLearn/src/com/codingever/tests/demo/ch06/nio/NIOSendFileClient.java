package com.codingever.tests.demo.ch06.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class NIOSendFileClient {
    // 客户端
    public static void client() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("C:\\Users\\DELL\\Pictures\\Saved Pictures\\Github.jpg"), StandardOpenOption.READ);
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8888));
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        long start = System.currentTimeMillis();
        while(inChannel.read(buffer) != -1){
            buffer.rewind();
            socketChannel.write(buffer);
            buffer.clear();
        }
        if(socketChannel != null) socketChannel.close();
        if(inChannel != null) inChannel.close();
        long end = System.currentTimeMillis();
        System.out.println("客户端发送文件耗时：" + (start - end));
    }

    /*由于文件存储在客户端，因此客户端才可以使用直接缓冲区，因为客户端可以知道文件的大小以及文件存储地址等文件描述符信息。
    * 服务端无法直接得知文件的大小，因此不能在内核地址空间中开辟一块准确大小的直接缓冲区，因此不建议在服务端使用直接缓冲区。*/

    public static void client2() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("C:\\Users\\DELL\\Pictures\\Saved Pictures\\Github.jpg"), StandardOpenOption.READ);
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8888));
        long start = System.currentTimeMillis();
        // 采用直接缓冲区
        inChannel.transferTo(0, inChannel.size(), socketChannel);
        if(socketChannel != null) socketChannel.close();
        if(inChannel != null) inChannel.close();
        long end = System.currentTimeMillis();
        System.out.println("客户端发送文件耗时：" + (start - end));
    }

    public static void main(String[] args) throws IOException {
        client();
        //client2();
    }
}
