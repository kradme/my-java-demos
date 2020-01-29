package cn.lang.netty.heartbeat.client;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;

import java.nio.channels.SocketChannel;

public class MyHeartbeatClientHandler extends SimpleChannelInboundHandler<SocketChannel> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketChannel msg) throws Exception {
        System.out.println("接收到server端报文："+msg);
    }
}
