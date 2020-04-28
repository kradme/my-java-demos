package cn.lang.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import java.nio.channels.FileChannel;
import java.util.Random;

public class NioDemo01 {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("input.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("output.txt");

        FileChannel inChannel = fileInputStream.getChannel();
        FileChannel outChannel = fileOutputStream.getChannel();

        try {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024*1024);

//            Random random = new Random();
//            long start = System.currentTimeMillis();
//            int index = 0;
//            for (int i = 0; i < 100000000; i++) {
//                long l = random.nextLong();
//                byteBuffer.put((String.valueOf(l)+"\n").getBytes());
//                if (byteBuffer.capacity()-byteBuffer.position()<1024){
//                    System.out.println(++index);
//                    byteBuffer.flip();
//                    outChannel.write(byteBuffer);
//                    byteBuffer.clear();
//                }
//            }
//            System.out.println((System.currentTimeMillis()-start));
//
            long start = System.currentTimeMillis();
            while (true){
                byteBuffer.clear();
                int read = inChannel.read(byteBuffer);
                if (read==-1) break;
                byteBuffer.flip();
                outChannel.write(byteBuffer);
            }
            System.out.println("耗时："+(System.currentTimeMillis()-start));
        } finally {
            inChannel.close();
            outChannel.close();;
        }
    }
}
