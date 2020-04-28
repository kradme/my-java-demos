package cn.lang.nio.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NioChatClient {
    public static void main(String[] args)  {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            Selector selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
//            socketChannel.connect(new InetSocketAddress("localhost", 8888));

            while (true){
                selector.select();

                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

                while (iterator.hasNext()){
                    SelectionKey selectionKey = iterator.next();
                    if (selectionKey.isConnectable()){

                        SocketChannel channel = (SocketChannel) selectionKey.channel();

                        if (channel.isConnectionPending()){
                            channel.finishConnect();

                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                            byteBuffer.put((LocalDateTime.now()+"连接成功").getBytes());
                            byteBuffer.flip();
                            socketChannel.write(byteBuffer);


                            ExecutorService executorService = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
                            executorService.submit(()->{
                               while (true){
                               BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                                       byteBuffer.clear();
                                        String line =bufferedReader.readLine();
                                       System.out.println(Thread.currentThread()+"--"+line);
                                       byteBuffer.put(line.getBytes());
                                       byteBuffer.flip();
                                       channel.write(byteBuffer);

                               }
                            });
                        }
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        iterator.remove();;
                    }else if (selectionKey.isReadable()){
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        channel.read(byteBuffer);
                        byteBuffer.flip();
                        String s = String.valueOf(Charset.forName("utf-8").decode(byteBuffer));
                        System.out.println(s);
                        iterator.remove();;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
