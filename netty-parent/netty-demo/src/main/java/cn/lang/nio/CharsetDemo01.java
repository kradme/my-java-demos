package cn.lang.nio;

import io.netty.util.CharsetUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.stream.IntStream;

public class CharsetDemo01 {
    public static void main(String[] args) {
        try (RandomAccessFile inFile = new RandomAccessFile("charsetDemo_in.txt", "r");
             RandomAccessFile outFile = new RandomAccessFile("charsetDemo_out.txt", "rw")) {

            FileChannel inFileChannel = inFile.getChannel();
            //内存映射
            MappedByteBuffer map = inFileChannel.map(FileChannel.MapMode.READ_ONLY, 0, inFile.length());
            CharBuffer charBuffer = map.asCharBuffer();
            


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
