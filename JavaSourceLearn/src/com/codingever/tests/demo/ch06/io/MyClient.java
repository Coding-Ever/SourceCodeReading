package com.codingever.tests.demo.ch06.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MyClient {
    public static void main(String[] args) throws IOException {
        // 客户端连接服务端发布的服务
        Socket socket = new Socket("127.0.0.1", 8888);
        // in用于接收服务端远程发来的文件，并将文件内容保存在内存中
        InputStream in = socket.getInputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        // 用于将内存中的文件输出到本地
        OutputStream fos = new FileOutputStream("C:\\Users\\DELL\\Desktop\\aaa.jpg");
        while((len = in.read(buffer)) != -1){
            fos.write(buffer, 0, len);
        }
        System.out.println("文件接收成功！");
        fos.close();
        in.close();
        socket.close();
    }
}
