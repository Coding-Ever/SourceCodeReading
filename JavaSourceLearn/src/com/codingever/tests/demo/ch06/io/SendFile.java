package com.codingever.tests.demo.ch06.io;

import java.io.*;
import java.net.Socket;

public class SendFile implements Runnable{
    private Socket socket;

    public SendFile(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("连接成功！");
            // out用于将内存中形成的文件远程发送给客户端
            OutputStream out = socket.getOutputStream();

            File file = new File("C:\\Users\\DELL\\Pictures\\Saved Pictures\\aaa.jpg");
            InputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int len = -1;
            while((len = fis.read(buffer)) != -1){
                out.write(buffer, 0, len);
            }

            fis.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
