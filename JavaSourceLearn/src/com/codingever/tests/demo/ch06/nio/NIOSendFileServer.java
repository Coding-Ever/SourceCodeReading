package com.codingever.tests.demo.ch06.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class NIOSendFileServer {
    /*NIO文件传输
     * 客户端向服务端发送文件，默认使用非直接缓冲区*/
    // 服务端
    public static void server() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        FileChannel outChannel = FileChannel.open(Paths.get("C:\\Users\\DELL\\Desktop\\Test\\GithubSendFile.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        serverSocketChannel.bind(new InetSocketAddress(8888));
        SocketChannel sChannel = serverSocketChannel.accept();
        System.out.println("连接成功...");
        long start = System.currentTimeMillis();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while(sChannel.read(buffer) != -1){
            buffer.flip();
            outChannel.write(buffer);
            buffer.clear();
        }
        System.out.println("接收成功！");
        sChannel.close();
        outChannel.close();
        serverSocketChannel.close();
        long end = System.currentTimeMillis();
        System.out.println("服务端接收文件耗时：" + (end - start));
    }

    public static void main(String[] args) throws IOException {
        server();
    }
}
