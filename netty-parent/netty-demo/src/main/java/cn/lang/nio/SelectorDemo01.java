package cn.lang.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;

public class SelectorDemo01 {
    public static void main(String[] args) {
        try {
            Selector selector = Selector.open();
            for (int i = 5000; i < 5008; i++) {
                ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
                serverSocketChannel.configureBlocking(false);
                ServerSocket socket = serverSocketChannel.socket();
                socket.bind(new InetSocketAddress(i));
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
                System.out.println("监听端口:"+i);
            }

            while (true){
                int select = selector.select();
                System.out.println("共获得："+select);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()){
                    SelectionKey selectionKey = iterator.next();
                    if (selectionKey.isAcceptable()){
                        ServerSocketChannel serverSocketChannel = ((ServerSocketChannel) selectionKey.channel());
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        iterator.remove();
                        System.out.println(Thread.currentThread()+"--获得客户端连接："+socketChannel);
                    }else if (selectionKey.isReadable()){
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        int bytesRead = 0;
                        while (true){
                            int read = socketChannel.read(byteBuffer);
                            bytesRead += read;
                            byteBuffer.flip();
                            socketChannel.write(byteBuffer);
                            if (byteBuffer.limit()==byteBuffer.capacity()){
                                byteBuffer.clear();
                            }else {
                                break;
                            }
                        }
                        System.out.println(Thread.currentThread()+"--接收到数据："+bytesRead+", 来自:"+socketChannel);
                        iterator.remove();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }
    }

}
