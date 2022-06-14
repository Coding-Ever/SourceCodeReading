package com.codingever.tests.demo.ch06.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class NIODemo {


    /*文件复制
    * 在直接缓冲区中，使用内存映射文件，并借助Channel完成文件的复制*/
    public static void test3() throws IOException{
        long start = System.currentTimeMillis();
        // 文件的输入通道
        FileChannel inchannel = FileChannel.open(Paths.get("C:\\Users\\DELL\\Pictures\\Saved Pictures\\Github.jpg"), StandardOpenOption.READ);
        // 文件的输出通道
        FileChannel outchannel = FileChannel.open(Paths.get("C:\\Users\\DELL\\Desktop\\Test\\Github1.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);
        // 输入通道和输出通道之间的内存映射文件（内存映射文件处于堆外内存中）
        MappedByteBuffer inMappedBuf = inchannel.map(FileChannel.MapMode.READ_ONLY, 0, inchannel.size());
        MappedByteBuffer outMappedBuf = outchannel.map(FileChannel.MapMode.READ_WRITE, 0, inchannel.size());

        // 直接对内存映射文件进行读写
        byte[] dst = new byte[inMappedBuf.limit()];
        inMappedBuf.get(dst);
        outMappedBuf.put(dst);
        inchannel.close();
        outchannel.close();

        long end = System.currentTimeMillis();
        System.out.println("复制操作消耗的时间（毫秒）：" + (end - start));
    }

    /*文件修改
    * 在直接缓冲区中，使用MappedByteBuffer修改文件中的内容*/
    public static void test4() throws IOException{
        RandomAccessFile raf = new RandomAccessFile("C:\\Users\\DELL\\Desktop\\Test\\test4.txt", "rw");
        FileChannel fileChannel = raf.getChannel();
        // mappedByteBuffer:代表了文件在内存中的映射文件
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, raf.length());
        mappedByteBuffer.put(1, (byte) 'X');
        // 只需要关心内存中的数据
        mappedByteBuffer.put(3, (byte) 'Y');
        raf.close();
    }

    /*零拷贝
     * 使用零拷贝内存的方式实现文件的复制*/
    public static void test5() throws IOException {
        long start = System.currentTimeMillis();
        FileChannel inChannel = FileChannel.open(Paths.get("C:\\Users\\DELL\\Pictures\\Saved Pictures\\Github.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("C:\\Users\\DELL\\Desktop\\Test\\Github5.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);
        inChannel.transferTo(0, inChannel.size(), outChannel);
        // 或
        //outChannel.transferFrom(inChannel, 0, inChannel.size());
        inChannel.close();
        outChannel.close();
        long end = System.currentTimeMillis();
        System.out.println("零拷贝复制文件消耗的时间（毫秒）：" + (end - start));
    }

    public static void main(String[] args) throws IOException {
        NIODemo nio = new NIODemo();
        //nio.test3();
        //nio.test4();
        test5();
    }

}
