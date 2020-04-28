package cn.lang.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class NioChatServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(8888));
        Map<String, SocketChannel> clientChannels = new HashMap<>();

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey selectedKey = iterator.next();
                if (selectedKey.isAcceptable()){

                    ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel) selectedKey.channel();
                    serverSocketChannel1.configureBlocking(false);
                    SocketChannel socketChannel = serverSocketChannel1.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    clientChannels.put(UUID.randomUUID().toString(), socketChannel);
                    System.out.println("客户端："+socketChannel+", 已连接");
                    iterator.remove();;
                }else if (selectedKey.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) selectedKey.channel();
                    socketChannel.configureBlocking(false);
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    while (true){
                        byteBuffer.clear();
                        int read = socketChannel.read(byteBuffer);
                        if (read<=0) break;;
                        byteBuffer.flip();
                        String decode = String.valueOf(Charset.forName("UTF-8").decode(byteBuffer));
                        System.out.println(decode);
                        clientChannels.forEach((key, client)->{
                            if (client!=socketChannel){

                                try {
                                    byteBuffer.flip();
                                    client.write(byteBuffer);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    iterator.remove();;
                }
            }
        }
    }
}
