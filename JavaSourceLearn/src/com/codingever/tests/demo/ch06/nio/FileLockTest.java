package com.codingever.tests.demo.ch06.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class FileLockTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        RandomAccessFile raf = new RandomAccessFile("C:\\Users\\DELL\\Desktop\\Test\\test4.txt", "rw");
        FileChannel fileChannel = raf.getChannel();
        /*
        * */
        FileLock fileLock = fileChannel.lock(2, 4, false);
        System.out.println("main线程将test4.txt锁3秒...");
        new Thread(() ->{
            try {
                byte[] buffer = new byte[8];
                // ②新线程对test4.txt进行读操作
                //raf.read(buffer, 0, 8);

                // ③新线程对test4.txt进行写操作
                raf.write("aaaa".getBytes(), 0, 4);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(3000);
        System.out.println("3秒结束，main释放锁！");
        fileLock.release();
    }
}
